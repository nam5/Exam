// Do it 안드로이드 롤리팝 Day 23 01 (youtube)
package com.suwonsmartapp.mythread;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private static final String TAG = "MainActivity";

    TextView textView;

    //ResponseHandler handler = new ResponseHandler();
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v) {
        Log.d(TAG, "첫번째 버튼 클릭됨,");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림");
        builder.setMessage("데이터를 저장하시겠습니까?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                textView.setText("스레드 시작함.");

                RequestThread thread = new RequestThread();
                thread.start();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }

    class RequestThread extends Thread {
        public void run() {
            for (int i = 0; 1 < 100; i++) {
                println("#" + i + " : 호출됨.");

                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }

        }

        public void println(final String data) {
            Log.d(TAG, data);

            //textView.setText(data);

            /*
            Message message = handler.obtainMessage();
            Bundle bundle = new Bundle();
            bundle.putString("data", data);
            message.setData(bundle);

            handler.sendMessage(message);
            */

            handler.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText(data);

                }
            });
        }
    }

    /*
    class ResponseHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            String data = bundle.getString("data");

            textView.setText(data);
        }
    }

    */


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
