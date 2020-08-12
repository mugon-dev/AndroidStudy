package com.example.menuapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Button button;
    int rotate;
    int scale;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("메뉴사용");
        linearLayout=findViewById(R.id.linerLayout1);
        button=findViewById(R.id.button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //지금 현재 액티비티가 가지고 있는 데뉴인플레터 가져오기
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.menu1,menu);
        menu.add(0,1,0,"배경색(빨강)");
        menu.add(0,2,0,"배경색(초록)");
        menu.add(0,3,0,"배경색(파랑)");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       /* switch (item.getItemId()){
            case R.id.itemRed:
                linearLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemGreen:
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemBlue:
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.subRotate:
                rotate+=10;
                if(rotate>=360) rotate=0;
                button.setRotation(rotate);
                break;
            case R.id.subSizeE:
                scale*=2;
                if(scale>10)scale=10;
                button.setScaleX(scale);
                button.setScaleY(scale);
                break;
            case R.id.subSizeC:
                scale/=2;
                if(scale<=1)scale=1;
                button.setScaleX(scale);
                button.setScaleY(scale);
                break;
        }*/
       switch (item.getItemId()){
           case 1:
               linearLayout.setBackgroundColor(Color.RED);
               break;
           case 2:
               linearLayout.setBackgroundColor(Color.GREEN);
               break;
           case 3:
               linearLayout.setBackgroundColor(Color.BLUE);
               break;
       }
        //메뉴선택이 안되었을때
        return true;
    }
}