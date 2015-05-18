package com.suwonsmartapp.timerapp01;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {



     private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = (Chronometer)findViewById(R.id.chronometer);

        Button start = (Button)findViewById(R.id.button1);
        start.setOnClickListener(this);

        Button stop = (Button)findViewById(R.id.button2);
        stop.setOnClickListener(this);

        Button reset = (Button)findViewById(R.id.button3);
        reset.setOnClickListener(this);
    }

    public static interface OnClickListener {
        public abstract void onClick(View v);
    }



    @Override
    public void onClick(View view) {
        Button button = (Button)view;

        switch (button.getId()) {
            case R.id.button1:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                break;
            case R.id.button2:
                chronometer.stop();
                break;
            case R.id.button3:
                chronometer.stop();
                chronometer.setBase(SystemClock.elapsedRealtime());
                break;
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
