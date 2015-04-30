package com.suwonsmartapp.mywidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import static android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * Created by Shaein on 2015-04-14.
 */
public class MultiCheckBox extends LinearLayout {

    public interface OnMultiChangeListener {
        public void onMultiChanged(boolean isFirstChecked, boolean isSecondChecked);
    }

    OnMultiChangeListener listener;

    public void setOnMultiChangeListener(OnMultiChangeListener lsnr) {

        listener = lsnr;
    }


    CheckBox checkBox;
    CheckBox checkBox2;

    public MultiCheckBox(Context context) {
        super(context);

        init(context);
    }


    public MultiCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.multi_checkbox, this, true);

        checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);

        checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (listener != null) {
                    listener.onMultiChanged(isChecked, checkBox2.isChecked());
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean b) {
                if (listener != null) {
                    listener.onMultiChanged(checkBox.isChecked(), b);
                }
            }
        });
    }

}







