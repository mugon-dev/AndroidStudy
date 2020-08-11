package com.example.eventapplication01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=findViewById(R.id.button2);
        b1.setOnClickListener(this);

       /* //이벤트 처리 기본 방법
        // class 만들고 객체 생성한 후 불러오기
        ClickListener listener = new ClickListener();
        b1=findViewById(R.id.button2);
        b1.setOnClickListener(listener);
        //요약방법
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"이벤트 처리 객체",Toast.LENGTH_LONG).show();
            }
        });*/
    }
   /* public void onBtnClicked(View view){
        Toast.makeText(getApplicationContext(),"버튼 클릭",Toast.LENGTH_LONG).show();
    }
    class ClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),"이벤트 처리 객체",Toast.LENGTH_LONG).show();
        }
    }*/

    @Override
    public void onClick(View view) {
        Toast.makeText(getApplicationContext(),"이벤트 처리 객체",Toast.LENGTH_LONG).show();
    }
}