package com.example.shaein.myapplication.challenge.challenge05;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shaein.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class doltMission05 extends ActionBarActivity {

    private EditText mNameEditText;
    private EditText mAgeEditText;
    private Button mSaveBtn;
    private Button mBirthdayBtn;

    private int year, month, day, hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dolt_mission05);

        mNameEditText = (EditText) findViewById(R.id.textView_name);
        mAgeEditText = (EditText) findViewById(R.id.textView_age);
        mBirthdayBtn = (Button) findViewById(R.id.btn_birthday);
        mSaveBtn = (Button) findViewById(R.id.btn_save);


        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(calendar.YEAR);
        month = calendar.get(calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(calendar.HOUR_OF_DAY);
        minute = calendar.get(calendar.MINUTE);
        mBirthdayBtn.setText(year + "년" + (month + 1) + "월" + "일");
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int monthofYear,
                          int dayofMonth) {
        // String msg = String.format("%d 년 %d 월 %d 일", year, monthofYear +
        //1, dayofMonth);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
        GregorianCalendar tmpCalendar = new GregorianCalendar();
        tmpCalendar.set(year, monthofYear, dayofMonth);

        mBirthdayBtn.setText(sf.format(tmpCalendar.getTime()));
        Toast.makeText(getApplicationContext(), sf.format(tmpCalendar.getTime()),
                Toast.LENGTH_SHORT).show();
    }


    };


}




// TinePickeerDialog 참고
/*private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

  @Override
  publid void onTimeSet(Timepicker view, int hourofDay, int minute) {
      String am_pm = "";

      Calendar datetime = Calendar.getInstance();
      datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
      datetime.set(Calendar.MINUTE, minute);

      if (datetime.get(Calendar.AM_PM) == Calendar.AM)
         am_pm = "AM";
      else if (datetime.get(Calendar.AM_PM) == Calendar.PM)

         am_pm = "PM";

      String strHrsToShow = (datetime.get(Calend.HOUR) == 0) ? "12" : datetime
               .get(Calendar.HOUR) + "";

      strHrsToShow += "시" + datetime.get(Calendar.MINUTE) + " 분 " + am_pm;

    }
  };*/




