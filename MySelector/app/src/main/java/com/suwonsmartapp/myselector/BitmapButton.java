package com.suwonsmartapp.myselector;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by Shaein on 2015-04-09.
 */
public class BitmapButton extends Button{

    public BitmapButton(Context context) {
        super(context);

        init();
    }

    public BitmapButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.apptheme_btn_check_holo_light);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();

        if (action == MotionEvent.ACTION_DOWN) {
            setBackgroundResource(R.drawable.apptheme_btn_check_off_pressed_holo_light);
        } else if (action == MotionEvent.ACTION_UP) {
            setBackgroundResource(R.drawable.apptheme_btn_check_holo_light);

        }

        return true;
    }
}
