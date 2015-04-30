package com.suwonsmartapp.myvideoplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;




public class MainActivity extends ActionBarActivity  {
    VideoView videoView;

    private static final int REQUEST_CODE_VIDEO = 0;

    private Button mBtnVideoFilePick;

    String url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnVideoFilePick = (Button) findViewById(R.id.button);


        videoView = (VideoView) findViewById(R.id.videoView);


        MediaController controller = new MediaController(this);
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus();

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "동영상 재생이 준비되었습니다.", Toast.LENGTH_LONG).show();

            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "동영상 재생이 완료되었습니다.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onButton1Clicked(View v) {

        Intent i = null;
        switch (v.getId()) {
            case R.id.button:
                // FileChooser 사용
                i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("video/*");
                startActivityForResult(Intent.createChooser(i, "파일선택..."), REQUEST_CODE_VIDEO);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_VIDEO && resultCode == RESULT_OK) {
           Uri fileUri = data.getData();

            videoView.setVideoURI(fileUri);
            videoView.start();

            mBtnVideoFilePick.setText(fileUri.getPath());


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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
