package com.example.animation2application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout page;
    Button btn;
    boolean isPageOpen = false; //페이지가 현재 열린 상태인가 닫힌 상태인가
    Animation translateLeft, translateRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        page=findViewById(R.id.page);
        btn = findViewById(R.id.button);
        translateLeft = AnimationUtils.loadAnimation(this,R.anim.left_translate);
        translateRight = AnimationUtils.loadAnimation(this,R.anim.right_translate);

        //애니메이션 시작, 끝, 반복할때 작업 정의의
       PageAnimationListener listener = new PageAnimationListener();
        translateRight.setAnimationListener(listener);
        translateLeft.setAnimationListener(listener);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPageOpen){
                    page.startAnimation(translateRight);
                    /*page.setVisibility(View.INVISIBLE);
                    isPageOpen = false;
                    btn.setText("Open");*/
                }else {
                    page.startAnimation(translateLeft);
                   /* page.setVisibility(View.VISIBLE);
                    isPageOpen = true;
                    btn.setText("Close");*/
                }
            }
        });
    }
    private class PageAnimationListener implements Animation.AnimationListener{

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(isPageOpen){
                btn.setText("Open");
                isPageOpen=false;
                page.setVisibility(View.INVISIBLE);
            }else{
                btn.setText("Close");
                isPageOpen=true;
                page.setVisibility(View.VISIBLE);
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}