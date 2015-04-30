//Do it 안드로이드 롤리팝 Day26 01(youtube)
package com.suwonsmartapp.mythreadanimation2;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Shaein on 2015-04-21.
 */
public class ThreadAnimationView extends ImageView {
    int[] imageArray = {R.drawable.bird, R.drawable.car, R.drawable.emot1, R.drawable.emot2};

    Handler handler = new Handler();


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


                       setImageResource(imageArray[index]);
                       invalidate();

                    }
                });

                try {
                    Thread.sleep(800);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                index++;
                if (index > 3) {
                    index = 0;
                }


            }
        }
    }

}
