package com.example.intenttestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edName,edAge;
    Button btn;
    String[] hobby = {"여행","게임","수영"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName=findViewById(R.id.editName);
        edAge=findViewById(R.id.editAge);
        btn=findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                int age = Integer.parseInt(edAge.getText().toString());
                //명시적 인텐트
                //현재 액티비티, 호출할 액티비티
                Intent intent = new Intent(MainActivity.this, OutputActivity.class);
                Member member = new Member(name,age,hobby);
                intent.putExtra("member",member);
                startActivity(intent);
            }
        });
    }
}