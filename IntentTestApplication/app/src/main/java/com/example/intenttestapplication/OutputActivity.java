package com.example.intenttestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {
    TextView tvResult;
    Button btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        Intent intent = getIntent();
        Member member = (Member) intent.getSerializableExtra("member");
//        String name = intent.getStringExtra("name");
//        //숫자는 default값 필요
//        int age= intent.getIntExtra("age",0);
//        String[] hobby = intent.getStringArrayExtra("hobby");
        tvResult=findViewById(R.id.tvResult);
        btnFinish=findViewById(R.id.btnFinish);
        String str="이름: "+member.getName();
        str+="\n 나이: "+member.getAge()+"\n 취미: ";
        for(int i=0; i<member.getHobby().length; i++){
            str+=member.getHobby()[i]+" ";
        }
        tvResult.setText(str);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}