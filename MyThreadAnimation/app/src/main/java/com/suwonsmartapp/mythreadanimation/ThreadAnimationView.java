package com.suwonsmartapp.mythreadanimation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;



/**
 * Created by Shaein on 2015-04-20.
 */
public class ThreadAnimationView extends ImageView{
   int[] imageArray =

    public ThreadAnimationView(Context context) {
        super(context);

        init(context);
    }

    public ThreadAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        ImageThread thread = new ImageThread();
        thread.start();


       //this.setImageResource();
    }

    class ImageThread extends Thread {
        public void run() {
            while (true) {

            }

        }
    }
}
