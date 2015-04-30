//Do it 안드로이드롤리팝 Day26 04
package com.suwonsmartapp.myframeanimation;

import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends ActionBarActivity {
    ImageView imageView;

    AnimationDrawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);

        Resources res = getResources();
        Drawable frame1 = res.getDrawable(R.drawable.bird);
        Drawable frame2 = res.getDrawable(R.drawable.car);
        Drawable frame3 = res.getDrawable(R.drawable.emot1);
        Drawable frame4 = res.getDrawable(R.drawable.emot2);

        int duration = 500;

        drawable = new AnimationDrawable();
        drawable.addFrame(frame1, duration);
        drawable.addFrame(frame2, duration);
        drawable.addFrame(frame3, duration);
        drawable.addFrame(frame4, duration);
        drawable.setOneShot(false);
    }
    public void onButton1Clicked(View v) {
        imageView.setBackground(drawable);

        drawable.setVisible(true,true);
        drawable.start();
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
