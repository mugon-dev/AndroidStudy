package com.example.mytestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    Chronometer chronometer;
    Button btnStart, btnEnd;
    RadioButton rbCal,rbTime;
    CalendarView calendarView;
    TimePicker timePicker;
    TextView tvResult;
    int selectYear, selectMonth, selectDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("시간 예약");
        btnStart=findViewById(R.id.btnStart);
        btnEnd=findViewById(R.id.btnEnd);
        chronometer=findViewById(R.id.chronometer1);
        rbCal=findViewById(R.id.rbCal);
        rbTime=findViewById(R.id.rbTime);
        calendarView=findViewById(R.id.calendarView);
        timePicker=findViewById(R.id.timePicker);
        tvResult=findViewById(R.id.tvResult);

        timePicker.setVisibility(View.INVISIBLE);
        calendarView.setVisibility(View.INVISIBLE);

        rbCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.setVisibility(View.INVISIBLE);
                calendarView.setVisibility(View.VISIBLE);
            }
        });
        rbTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePicker.setVisibility(View.VISIBLE);
                calendarView.setVisibility(View.INVISIBLE);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                chronometer.setTextColor(Color.RED);
            }
        });
        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.stop();
                chronometer.setTextColor(Color.BLUE);
                String str=selectYear+"년 ";
                str+=selectMonth+"월 ";
                str+=selectDay+"일 ";
                str+=timePicker.getCurrentHour()+"시 ";
                str+=timePicker.getCurrentMinute()+"분 예약됨";
                tvResult.setText(str);
            }
        });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selectYear=year;
                //month는 0부터 시작
                selectMonth=month+1;
                selectDay=day;
            }
        });

    }
}