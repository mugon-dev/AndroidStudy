package com.example.listviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    String[] text = {"히어로즈","24시","로스트","로스트룸","스몰빌","탐정몽크","빅뱅이론","프랜즈","덱스트","가쉽걸","데이큰","슈퍼네추럴"};
    ArrayAdapter<String> adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        lv=findViewById(R.id.listView1);
        adapter = new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_multiple_choice,text
        );
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                tv.setText(text[position]);

            }
        });
    }
}