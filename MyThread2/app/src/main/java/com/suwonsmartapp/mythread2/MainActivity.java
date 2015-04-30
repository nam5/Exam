//Do it 안드로이드 롤리팝 Day24 02(youtube)
package com.suwonsmartapp.mythread2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    ProcessThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread = new ProcessThread();
        thread.start();


    }

    public void onButton1Clicked(View v) {
        thread.ProcessHandler.post(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "메인 스레드에서 새로운 스레드로 전달됨.");

            }
        });

    }

    class ProcessThread extends Thread {

        Handler ProcessHandler = new Handler();

        public ProcessThread() {


        }

        public void run() {
            Looper.prepare();
            Looper.loop();

            for (int i = 0; i< 100; i++) {
                Log.d(TAG, "스레드 동작중 : #" + i);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {}
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
