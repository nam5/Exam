package com.suwonsmartapp.mycamera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Shaein on 2015-04-19.
 */
public class MyImageView extends View {
    Context mContext;
    Bitmap mBitmap;
    Canvas mCanvas;

    Paint paint;

    Bitmap characters;

    Camera camera = new Camera();


    public MyImageView(Context context) {
        super(context);

        init(context);
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        mContext = context;

        paint = new Paint();

        characters = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.characters);

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

            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawBitmap(characters, 0, 0, paint);

            camera.save();
            Matrix matrix = new Matrix();
            camera.rotateY(45.0f);
            camera.translate(0.0f, 0.0f, 100.0f);
            camera.getMatrix(matrix);
            camera.restore();

            Bitmap rotatedCharacters = Bitmap.createBitmap(characters, 0, 0, characters.getWidth(), characters.getHeight(), matrix, true);
            mCanvas.drawBitmap(rotatedCharacters, 100, 100, paint);
        }

        super.onSizeChanged(w, h, oldw, oldh);
    }
}
