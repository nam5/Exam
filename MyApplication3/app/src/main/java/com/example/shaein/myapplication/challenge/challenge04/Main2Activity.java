package com.example.shaein.myapplication.challenge.challenge04;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaein.myapplication.R;

public class Main2Activity extends ActionBarActivity {

    EditText mId;
    EditText mPassWord;

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mId = (EditText) findViewById(R.id.idEditText);
        //mPassWord = (EditText) findViewById(R.id.pa);

        mButton = (Button) findViewById(R.id. loginBtn);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mId.getText()) || TextUtils.isEmpty(mPassWord.getText())) {
                    Toast.makeText(getApplicationContext(), "ID, password �� �Է��ϼ���",
                            Toast.LENGTH_SHORT).show();
                 } else {
                     startActivity(new Intent(getApplicationContext(), SubActivity.class));
                }
            }
        });

     }

}
