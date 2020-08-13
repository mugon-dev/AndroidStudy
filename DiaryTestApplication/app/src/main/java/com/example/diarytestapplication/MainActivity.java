package com.example.diarytestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    EditText ed;
    Button button, button2, button3;
    int selectYear, selectMonth, selectDay;
    String filename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarView=findViewById(R.id.calendarView);
        ed=findViewById(R.id.editDiary);
        button=findViewById(R.id.button);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);

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

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    //raw 폴더에서 불러오기
                    InputStream is = getResources().openRawResource(R.raw.test01);
                    //파일 크기만큼 크기지정
                    byte[] txt=new byte[is.available()];
                    is.read(txt);
                    ed.setText(new String(txt));
                    is.close();
                }catch (Exception e){

                }
            }
        });
        //sd카드에서 읽어오기
        ActivityCompat.requestPermissions(this,new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileInputStream is = new FileInputStream("/sdcard/myDir/test01.txt");
                    byte[] txt = new byte[is.available()];
                    is.read(txt);
                    ed.setText(new String(txt));
                    is.close();
                }catch (IOException e){

                }
            }
        });
    }

}