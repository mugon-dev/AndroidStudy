package com.example.mytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    }
}