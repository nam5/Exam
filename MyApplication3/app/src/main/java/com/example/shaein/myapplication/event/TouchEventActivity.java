package com.example.shaein.myapplication.event;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shaein.myapplication.R;

public class TouchEventActivity extends ActionBarActivity {

    TextView mTextView;

    Button mEventBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_event);

         mTextView = (TextView) findViewById(R.id.textView);
//       mEventBtn = (Button) findViewById(R.id.eventBtn);

        mEventBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    v.setBackgroundColor(Color.BLUE);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    v.setBackgroundColor(Color.CYAN);
                }

                mTextView.setText(event.toString());

                return false;


            }
        });

        mEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ŭ��", Toast.LENGTH_SHORT).show();
            }
        });
    }
 // ȭ���� ��ġ�̺�Ʈ ó��
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // mTextView.setText(event,toString());


        return super.onTouchEvent(event);
    }
}
