package com.example.activitytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    Button btnFinish;
    TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //intent 값 받기
        Intent intent = getIntent();
        //객체 생성해서 받아온 값 찾아서 넣기
        String name = intent.getStringExtra("name");
        String age = intent.getStringExtra("age");
        btnFinish=findViewById(R.id.btnFinish);
        tvResult=findViewById(R.id.tvResult);
        tvResult.setText(name+", "+age);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}