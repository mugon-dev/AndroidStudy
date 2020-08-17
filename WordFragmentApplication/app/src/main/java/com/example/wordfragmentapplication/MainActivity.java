package com.example.wordfragmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements WordFragment.OnWordSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.word_container)!=null){
            if(savedInstanceState != null){
                return;
            }
            WordFragment fragment = new WordFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.word_container,fragment).commit();
        }
    }
    //프래그먼트끼리 데이터 전송할때 부착되는 메인액티비티를 통해서 전달해야함
    @Override
    public void onWordSelected(int position) {
        DefinitionFragment deFragment= (DefinitionFragment) getSupportFragmentManager().findFragmentById(R.id.definition_container);
        if(deFragment != null){
            deFragment.updateDefinitionView(position);
        }else{
            DefinitionFragment newFragment=new DefinitionFragment();
            Bundle args=new Bundle();
            args.putInt(DefinitionFragment.ARG_POSITION/*=="position"*/,position); //position 이라는 이름으로 argument 값으로 position 전달
            newFragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.definition_container,newFragment);
            //ft.replace(R.id.definition_container,newFragment);
            //ft.addToBackStack(null);
            ft.commit();
        }

    }
}