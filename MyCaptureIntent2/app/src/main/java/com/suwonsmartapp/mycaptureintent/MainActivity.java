//Do it 안드로이드 롤리팝 Day36 01(youtube)
package com.suwonsmartapp.mycaptureintent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends ActionBarActivity {
    ImageView imageView;

    File outputFile;

    FrameLayout container;
    CameraSurfaceView cameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.imageView);
        container = (FrameLayout) findViewById(R.id.container);

        cameraView = new CameraSurfaceView(this);
        container.addView(cameraView);

        File storageDir = Environment.getExternalStorageDirectory();
        outputFile = new File(storageDir, "output.jpg");

    }

    public void onButton1Clicked(View v) {
        /*
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(outputFile));
        intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
                ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        startActivityForResult(intent, 1001);
        */

        cameraView.capture(new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] data, Camera camera) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 0;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
                imageView.setImageBitmap(bitmap);

                camera.startPreview();
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 0;
            Bitmap bitmap = BitmapFactory.decodeFile(outputFile.getAbsolutePath(), options);
            imageView.setImageBitmap(bitmap);
        }
    }

    class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
        SurfaceHolder holder;
        Camera camera = null;

        CameraSurfaceView(Context context) {
            super(context);

            init(context);
        }

        CameraSurfaceView(Context context, AttributeSet attrs) {
            super(context, attrs);

            init(context);
        }

        private void init(Context context) {
            holder = getHolder();
            holder.addCallback(this);

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                camera = Camera.open();
                camera.setPreviewDisplay(holder);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            camera.startPreview();

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            camera.startPreview();
            camera.release();
            camera = null;

        }

        public boolean capture(Camera.PictureCallback callback) {
            if (camera != null) {
                camera.takePicture(null, null, callback);
                return true;

            } else {
                return false;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
