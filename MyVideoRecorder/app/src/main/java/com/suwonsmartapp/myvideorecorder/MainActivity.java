//Do it 안드로이드 롤리팝 Day35 02(youtube)
package com.suwonsmartapp.myvideorecorder;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    SurfaceView surfaceView;
    SurfaceHolder holder;
    MediaRecorder recorder;

    String path = "/sdcard/recorded_video.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
    }

    public void onButton1Clicked(View v) {

        try {
            recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);

            recorder.setOutputFile(path);

            recorder.setPreviewDisplay(holder.getSurface());
            recorder.prepare();
            recorder.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "녹화를 시작합니다.", Toast.LENGTH_LONG).show();



    }

    public void onButton2Clicked(View v) {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }

        Toast.makeText(getApplicationContext(), "녹화가 중지되었습니다.", Toast.LENGTH_LONG).show();

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
