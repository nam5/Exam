package com.suwonsmartapp.mycustomview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Shaein on 2015-04-17.
 */
public class MyView extends View {
    Paint paint;

    Bitmap mBitmap;
    Canvas mCanvas;

    Context mContext;

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
        paint.setAntiAlias(true);

        mContext = context;


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w > 0 && h > 0) {
            mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mCanvas = new Canvas();
            mCanvas.setBitmap(mBitmap);

            draw1();

        }


        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void draw1() {

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        mCanvas.drawRect(100, 100, 200, 200, paint);



        paint.setStyle(Paint.Style.STROKE);
        paint.setARGB(128, 0, 255, 0);
        paint.setStrokeWidth(10.0f);
        mCanvas.drawRect(100, 100, 200, 200, paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLUE);

        DashPathEffect effect = new DashPathEffect(new float[]{5,5}, 1);
        paint.setPathEffect(effect);
        mCanvas.drawLine(100, 300, 500, 500, paint);

        ShapeDrawable drawable1 = new ShapeDrawable();
        RectShape shape1 = new RectShape();
        shape1.resize(200, 200);
        drawable1.setShape(shape1);
        drawable1.setBounds(300, 100, 500, 300);

        drawable1.draw(mCanvas);

        LinearGradient gradient1 = new LinearGradient(0, 0, 0, 200, Color.RED, Color.YELLOW, Shader.TileMode.CLAMP);
        paint.setShader(gradient1);


        shape1.resize(300, 300);
        drawable1.setBounds(400, 300, 700, 600);
        drawable1.draw(mCanvas);

        Bitmap balloonBitmap = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.balloon);
        mCanvas.drawBitmap(balloonBitmap, 500, 500, paint);

        Matrix matrix1 = new Matrix();
        matrix1.setScale(1, -1);
        Bitmap balloonBitmap2 = Bitmap.createBitmap(balloonBitmap, 0, 0, balloonBitmap.getWidth(), balloonBitmap.getHeight(), matrix1, false);
        mCanvas.drawBitmap(balloonBitmap2, 800, 200, paint);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //void drawPoint(floatX, floatY, Paint paint) =점 그리기
        //void drawLine(float startX, float startY, float stopX, float stopY, Paint paint) = 선 그리기
        //void drawRect(float left, float top, float right, float bottom, Paint paint) = 사각형 그리기
        //void drawRoundRect(RectF rect, float rX, float ry, Paint paint) = 둥근 모서리의 사각형 그리기
        //void drawCircle(float cx, float cy, float radius Paint paint) =원 그리기
        //void drawOval(RectF oval, Paint paint) =타원 그리기
        //void drawArc(RectF oval, float startAngle, float sweepAngle boolean useCenter Paint paint) = 아크 그리기
        //*void drawPath(Path path, Paint paint) = 패스 그리기(연속된 점을 가지고 선을 그리는 것, 직선뿐만 아니라 곡선도 그릴수 있음)
        //void drawBitmap(Bitmap bitmap, float left, float top, Paint paint) = 비트맵 그리기

        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, null);
        }
    }
}
