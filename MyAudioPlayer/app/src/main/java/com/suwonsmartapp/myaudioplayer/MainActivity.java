//Do it 안드로이드 롤리팝 Day 35 01(youtube)
package com.suwonsmartapp.myaudioplayer;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    MediaPlayer Player;
    MediaRecorder recorder;

    private static final int REQUEST_CODE_AUDIO = 0;


    String path = "/sdcard/recorded.mp4";


    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        }


    public void onButton1Clicked(View v) {
        Log.d("MainActivity", "시작 버튼 클릭됨.");
        //음악파일을 듣기 위해선 Intent밑으로 주석을 풀어야함.
        //Intent i = null;

        try {
            // FileChooser 사용
            //i = new Intent(Intent.ACTION_GET_CONTENT);
            //i.setType("audio/*");
            //startActivityForResult(Intent.createChooser(i, "파일선택..."), REQUEST_CODE_AUDIO);

            killPlayer();


            Player = new MediaPlayer();
            Player.setDataSource(path);
            Player.prepare();
            Player.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        Toast.makeText(getApplicationContext(), "재생를 시작합니다.", Toast.LENGTH_LONG).show();


    }

    private void killPlayer() {
        if (Player !=null) {
            Player.release();
            Player = null;
        }
    }

    public void onButton2Clicked(View v) {
        Log.d("MainActivity", "일시정지 버튼 클릭됨.");

        if (Player != null && Player.isPlaying()) {
            position = Player.getCurrentPosition();
            Player.pause();
        }

        Toast.makeText(getApplicationContext(), "재생를 일시정지 합니다.", Toast.LENGTH_LONG).show();

    }

    public void onButton3Clicked(View v) {
        Log.d("MainActivity", "재시작 버튼 클릭됨.");

        if (Player != null && !Player.isPlaying()) {
           Player.start();
           Player.seekTo(position);
       }

        Toast.makeText(getApplicationContext(), "재시작 합니다.", Toast.LENGTH_LONG).show();

    }

    public void onButton4Clicked(View v) {
        Log.d("MainActivity", "중지 버튼 클릭됨.");

        if (Player != null && Player.isPlaying()) {
            Player.stop();
        }

        Toast.makeText(getApplicationContext(), "재생를 중지합니다.", Toast.LENGTH_LONG).show();

    }

    public void onButton5Clicked(View v) {

        try {
            if (recorder != null) {
                recorder.stop();
                recorder.release();
                recorder = null;
            }

            recorder = new MediaRecorder();

            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

            recorder.setOutputFile(path);

            recorder.prepare();
            recorder.start();

            Toast.makeText(getApplicationContext(), "녹음을 시작합니다.", Toast.LENGTH_LONG).show();


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void onButton6Clicked(View v) {
        if (recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;

            Toast.makeText(getApplicationContext(), "녹음을 중지합니다.", Toast.LENGTH_LONG).show();


        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        killPlayer();
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
