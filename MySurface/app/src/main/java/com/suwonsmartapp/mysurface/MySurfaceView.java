package com.suwonsmartapp.mysurface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

/**
 * Created by Shaein on 2015-04-18.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    Context mContext;
    SurfaceHolder holder;
    Paint paint;



    public MySurfaceView(Context context) {
        super(context);

        init(context);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        this.holder = getHolder();
        this.holder = addCallback(this);

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        paint.setStrokeWidth(10.0f);

    }


    public void doDraw() {
        Toast.makeText(mContext, "doDrow() 호출됨.", Toast.LENGTH_LONG).show();

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Toast.makeText(mContext,"onDrow() 호출됨.", Toast.LENGTH_LONG).show();

        canvas.drawRect(100, 100, 200, 200, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Toast.makeText(mContext, "surfaceCreated() 호출됨.", Toast.LENGTH_LONG).show();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Toast.makeText(mContext, "surfaceCreated() 호출됨 : " + width + "," + height, Toast.LENGTH_LONG).show();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Toast.makeText(mContext, "surfaceCreated() 호출됨.", Toast.LENGTH_LONG).show();

    }
}
