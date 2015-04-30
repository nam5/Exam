
package com.example.shaein.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shaein.myapplication.R;

public class MainActivity extends ActionBarActivity {

    private Button testButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_Main);

       // testButton = (Button) findViewById(R.id.image);  // 버튼 객체 가지고 온다

        //버튼 객체에 onClick 이벤트를 연결한다
       // testButton.setOnCliListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
                // 버튼이 클릭 되었을 때 수행
             //   onButton1Clicked(v);
          //  }
       // });

       // testButton.callOnClick();

    }


    public void onButton1Clicked(View v) {
        Toast.makeText(getApplicationContext(), "버튼 클릭", Toast.LENGTH_SHORT).show();
    }

    public void onButton2Clicked(View v) {
        Toast.makeText(getApplicationContext(), "버튼 클릭", Toast.LENGTH_SHORT).show();
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

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
