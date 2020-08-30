package com.example.fragmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.container)!=null){
            //현재 액티비티의 상태
            if (savedInstanceState != null) {
                //화면이 정상적으로 실행되는 상태 == 이미 프래그먼트가 들어있음
                return;
            }
            AFragment fragment = new AFragment();
            //컨테이너에 프래그먼트 넣기기
           getSupportFragmentManager().beginTransaction().add(R.id.container,fragment).commit();
//            BFragment fragment = new BFragment();
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//            ft.add(R.id.container,fragment);
//            ft.commit();
        }
    }
    public void selectFragment(View v){
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.buttonA: fragment = new AFragment(); break;
            case R.id.buttonB: fragment = new BFragment(); break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //이미 fragment가 붙어있을때
        ft.replace(R.id.container,fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}