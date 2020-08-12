package com.example.menuapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
        //context 메뉴 등록
        registerForContextMenu(button);
        registerForContextMenu(linearLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        //res의 menu폴더에서 menu가져오기
        if(v==button){
            menu.setHeaderTitle("배경색");
            inflater.inflate(R.menu.menu3,menu);
        }
        if(v==linearLayout){
            menu.setHeaderTitle("버튼 설정");
            inflater.inflate(R.menu.menu2,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.itemContextRed:
                linearLayout.setBackgroundColor(Color.RED);
                break;
            case R.id.itemContextGreen:
                linearLayout.setBackgroundColor(Color.GREEN);
                break;
            case R.id.itemContextBlue:
                linearLayout.setBackgroundColor(Color.BLUE);
                break;
            case R.id.itemRotate:
                rotate+=10;
                if(rotate>=360) rotate=0;
                button.setRotation(rotate);
                break;
            case R.id.itemSizeE:
                scale*=2;
                if(scale>10)scale=10;
                button.setScaleX(scale);
                button.setScaleY(scale);
                break;
            case R.id.itemSizeC:
                scale/=2;
                if(scale<=1)scale=1;
                button.setScaleX(scale);
                button.setScaleY(scale);
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //지금 현재 액티비티가 가지고 있는 데뉴인플레터 가져오기
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
//        menu.add(0,1,0,"배경색(빨강)");
//        menu.add(0,2,0,"배경색(초록)");
//        menu.add(0,3,0,"배경색(파랑)");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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
        }
        /*switch (item.getItemId()){
           case 1:
               linearLayout.setBackgroundColor(Color.RED);
               break;
           case 2:
               linearLayout.setBackgroundColor(Color.GREEN);
               break;
           case 3:
               linearLayout.setBackgroundColor(Color.BLUE);
               break;
       }*/
        //메뉴선택이 안되었을때
        return true;
    }
}