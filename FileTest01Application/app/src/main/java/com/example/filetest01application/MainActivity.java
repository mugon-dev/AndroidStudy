package com.example.filetest01application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Button btnWrite, btnRead;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.edit1);
        btnRead=findViewById(R.id.btnRead);
        btnWrite=findViewById(R.id.btnWrite);
        tv=findViewById(R.id.textView);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    FileOutputStream os = openFileOutput("test.txt", Context.MODE_PRIVATE);
                    os.write((ed.getText().toString()).getBytes());
                    os.close();
                }catch (IOException ex){

                }
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream is = openFileInput("test.txt");
                    byte[] txt = new byte[100];
                    tv.setText(new String(txt));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}