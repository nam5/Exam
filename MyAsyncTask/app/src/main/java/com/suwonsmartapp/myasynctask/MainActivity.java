//Do it 안드로이드 롤리팝 Day 25 01(youtube)
package com.suwonsmartapp.myasynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    TextView textView;
    ProgressBar progressBar;

    int value = 0;

    BackgroundTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    public void onButton1Clicked(View v) {
        task = new BackgroundTask();
        task.execute(100);
    }


    public void onButton2Clicked(View v) {
        task.cancel(true);
    }


    class BackgroundTask extends AsyncTask<Integer, Integer, Integer> {

        @Override
        protected void onPreExecute() {
            value = 0;
            progressBar.setProgress(value);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            value = 0;
            progressBar.setProgress(value);

            textView.setText("중지됨.");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());
            textView.setText("진행중 : " + values[0]);
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            while (!isCancelled()) {
                value++;
                if (value >= 100) {
                    break;
                } else {
                    publishProgress(value);
                }
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                }
            }
            return value;
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


