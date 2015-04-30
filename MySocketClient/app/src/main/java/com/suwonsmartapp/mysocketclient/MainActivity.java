//Do it 안드로이드 롤리팝 Day 28 03(youtube)
//이크립스에서 javaSocketServer 과 javaSocketClient를 만들고 안드로이드에 붙인 작업
package com.suwonsmartapp.mysocketclient;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class MainActivity extends ActionBarActivity {
    TextView textView;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
    }

    public void onButton1Clicked(View v) {

        ConnectThread thread = new ConnectThread();
        thread.start();

    }

    class ConnectThread extends Thread {
        public void run() {

            String host = "192.168.0.51";
            int port = 5001;

            try {
                Socket socket = new Socket(host, port);
                println("서버로 연결되었습니다. : " + host + ", " + port);

                String output = "Hello";
                ObjectOutputStream outstream = new ObjectOutputStream(socket.getOutputStream());
                outstream.writeObject(output);
                outstream.flush();
                println("서버로 보낸 데이터 : " + output);

                ObjectInputStream instream = new ObjectInputStream(socket.getInputStream());
                Object input = instream.readObject();
                println("서버로 부터 받은 데이터 : " + input);

                instream.close();
                outstream.close();
                socket.close();


            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }

    private void println(final String data) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.append(data + "\n");
            }
        });

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
