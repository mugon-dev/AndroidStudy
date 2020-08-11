package com.example.autocompleteapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    String[] items={"CSI-뉴욕","CSI-라스베가스","CSI-마이애미","Friends","Fringe","Lost"};
    AutoCompleteTextView autoTv;
    MultiAutoCompleteTextView mulAutoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        autoTv=findViewById(R.id.autoCompleteTextView);
        mulAutoTv=findViewById(R.id.multiAutoCompleteTextView);
        //안드로이드 adpater는 connection 값저장하는 변수
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,items);
        autoTv.setAdapter(adapter);

        MultiAutoCompleteTextView.CommaTokenizer token = new MultiAutoCompleteTextView.CommaTokenizer();
        mulAutoTv.setTokenizer(token);
        mulAutoTv.setAdapter(adapter);
    }
}