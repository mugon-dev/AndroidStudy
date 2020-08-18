package com.example.cardviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {
    RecyclerView recyclerView;
    //LinearLayoutManager layoutManager;
    GridLayoutManager layoutManager;
    PersonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView=findViewById(R.id.recyclerView1);
        /*layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );*/
        layoutManager = new GridLayoutManager(
                this,2
        );
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PersonAdapter();
        adapter.addItem(new Person("홍길동","010-1111-2222"));
        adapter.addItem(new Person("한재현","010-1111-2222"));
        adapter.addItem(new Person("이하윤","010-1111-2222"));
        adapter.addItem(new Person("김림용","010-1111-2222"));
        adapter.addItem(new Person("이현지","010-1111-2222"));
        adapter.addItem(new Person("조양현","010-1111-2222"));
        recyclerView.setAdapter(adapter);
    }
}