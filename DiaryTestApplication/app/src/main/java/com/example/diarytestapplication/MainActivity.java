package com.example.diarytestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    EditText ed;
    Button button;
    int selectYear, selectMonth, selectDay;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=findViewById(R.id.calendarView);
        ed=findViewById(R.id.editDiary);
        button=findViewById(R.id.button);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                selectYear = i;
                selectMonth = i1+1;
                selectDay = i2;
                ed.setText("");
                button.setText("저장");
                filename="diary_"+selectYear+"_"+selectMonth+"_"+selectDay+".txt";
                byte[] txt = new byte[100];
                try{
                    FileInputStream is = openFileInput(filename);
                    is.read(txt);
                    String str = new String(txt);
                    ed.setText(str);
                    button.setText("수정");
                    is.close();
                }catch (IOException e){

                }
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileOutputStream os =null;
                try{
                    os=openFileOutput(filename, Context.MODE_PRIVATE);
                    os.write((ed.getText().toString()).getBytes());
                    os.close();
                    ed.setText("");
                    Toast.makeText(MainActivity.this,"저장되었습니다.",Toast.LENGTH_SHORT).show();
                }catch (IOException ex){

                }
            }
        });
    }

}