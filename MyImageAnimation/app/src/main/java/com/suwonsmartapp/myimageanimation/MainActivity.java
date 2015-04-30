package com.suwonsmartapp.myimageanimation;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


public class MainActivity extends ActionBarActivity {
    int[] imageArray = {R.drawable.bird, R.drawable.car, R.drawable.emot1, R.drawable.emot2};
    ImageSwitcher imageSwitcher;

    Handler handler = new Handler();

    ImageThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;

            }
        });

        Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);

        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);



    }

    public void onButton1Clicked(View v) {
        thread = new ImageThread();
        thread.start();

    }

    public void onButton2Clicked(View v) {
        if (thread != null) {
            thread.halt();
            thread = null;
        }

    }
    class ImageThread extends Thread {
        boolean running = false;
        int index = 0;
        int interval = 800;

        public void run() {
            running = true;



            while (running) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {


                        imageSwitcher.setImageResource(imageArray[index]);
                        imageSwitcher.invalidate();

                    }
                });

                try {
                    Thread.sleep(interval);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                index++;
                if (index > 3) {
                    index = 0;
                }


            }
        }

        public void halt() {
            running = false;
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
