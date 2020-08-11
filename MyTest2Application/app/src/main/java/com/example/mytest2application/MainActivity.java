package com.example.mytest2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper vf;
    Button btnPrev, btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vf=findViewById(R.id.viewFlipper);
        btnNext=findViewById(R.id.btnNext);
        btnPrev=findViewById(R.id.btnPrev);
        //클릭시 이전 이후 사진
       /* btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.showPrevious();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vf.showNext();
            }
        });*/
       btnNext.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               vf.startFlipping();
               vf.setFlipInterval(1000);
           }
       });
       btnPrev.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               vf.stopFlipping();
           }
       });
    }
}