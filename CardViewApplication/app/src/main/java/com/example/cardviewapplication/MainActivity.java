package com.example.cardviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn1,btn2;
    Layout1 layout1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout1=findViewById(R.id.layout1);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        layout1.setImage(R.drawable.profile1);
        layout1.setName("홍길동");
        layout1.setPhone("010-1111-2222");
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setImage(R.drawable.profile1);
                layout1.setName("홍길동");
                layout1.setPhone("010-1111-2222");
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout1.setImage(R.drawable.profile2);
                layout1.setName("김민수");
                layout1.setPhone("010-2222-3333");
            }
        });

    }
}