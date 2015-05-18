package com.suwonsmartapp.mypaintboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Shaein on 2015-04-18.
 */
public class PaintBoard extends View {
    //Context : 일반적으로 어떤 일이 발생한 상황을 의미하는 말(프로그래밍 언어에서는 객체의 상태정보를 표현)
    //안드로이드에서는 UI 구성요소인 View에 대한 정보를 손쉽게 확인하거나 설정할 수 있도록 뷰의 생성자에
    //Context 객체를 전달하도록 되어 있음.
    Context mContext;
    Paint paint;

    Bitmap mBitmap;
    Canvas mCanvas;


    float oldX;
    float oldY;
    float curX;
    float curY;

    Path path;


    public PaintBoard(Context context) {
        super(context);

        init(context);
    }

    public PaintBoard(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10.0f);

        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas();
            mCanvas.setBitmap(mBitmap);
        }
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        curX = event.getX();
        curY = event.getY();




        if (action == MotionEvent.ACTION_DOWN) {
            path.moveTo(curX, curY);

        } else if (action == MotionEvent.ACTION_MOVE) {
            //mCanvas.drawLine(oldX, oldY, curX, curY, paint);

            float centerX = (curX + oldX) / 2.0f;
            float centerY = (curY + oldY) / 2.0f;
            path.quadTo(oldX, oldY, centerX, centerY);

            mCanvas.drawPath(path, paint);

        } else if (action == MotionEvent.ACTION_UP) {

        }

        invalidate();

        oldX = curX;
        oldY = curY;

        return true;
    }
}
