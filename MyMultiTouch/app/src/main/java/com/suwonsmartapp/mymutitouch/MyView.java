package com.suwonsmartapp.mymutitouch;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Shaein on 2015-04-15.
 */
public class MyView extends View {
    private static final String TAG = "MyView";

    float curX1;
    float curY1;
    float curX2;
    float curY2;

    float oldX1;
    float oldY1;
    float oldX2;
    float oldY2;

    float diffX1;
    float diffY1;


    Paint paint;
    Bitmap bitmap;

    Bitmap mBitmap1;
    Canvas mCanvas;

    public MyView(Context context) {
        super(context);

        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        paint = new Paint();

        Resources res = context.getResources();
        bitmap = BitmapFactory.decodeResource(res, R.drawable.beach);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
             newImage(w, h);

            redraw();

        }
    }

    private void newImage(int w, int h) {
        mBitmap1 = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap1);

    }

    private void redraw() {
        mCanvas.drawColor(Color.WHITE);
        mCanvas.drawBitmap(bitmap, diffX1, diffY1, paint);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mBitmap1 != null) {
            canvas.drawBitmap(mBitmap1, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        int pointerCount = event.getPointerCount();

        curX1 = event.getX(0);
        curY1 = event.getY(0);

        if (pointerCount > 1) {
            curX2 = event.getY(1);
            curY2 = event.getY(1);
        }
        if (action == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "손가락이 눌렸습니다. : " + pointerCount + "," + curX1 + "," + curY1 + "," + curX2 + "," + curY2);
        } else if (action == MotionEvent.ACTION_MOVE) {
            Log.d(TAG, "손가락이 움직였습니다. : " + pointerCount + "," + curX1 + "," + curY1 + "," + curX2 + "," + curY2);

            diffX1 = curX1;
            diffY1 = curY1;

            redraw();

        } else if (action == MotionEvent.ACTION_UP) {
            Log.d(TAG, "손가락이 빠졌습니다. : " + pointerCount + "," + curX1 + "," + curY1 + "," + curX2 + "," + curY2);

        }

        oldX1 = curX1;
        oldY1 = curY1;
        oldX2 = curX2;
        oldY2 = curY2;

        return true;


    }
}
