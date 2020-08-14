package com.example.intent2application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        btn=findViewById(R.id.button2);
        Intent intent = getIntent();
        int num1 = intent.getIntExtra("num1",0);
        int num2 = intent.getIntExtra("num2",0);
        final int sum=num1+num2;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(),MainActivity.class);
                intent1.putExtra("sum",sum);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });
    }
}