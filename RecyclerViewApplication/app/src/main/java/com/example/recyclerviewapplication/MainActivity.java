package com.example.recyclerviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MovieAdapter adapter;
    int[] resId = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
    String[] title = {"써니", "완득이", "괴물", "라디오스타", "비열한거리", "왕의남자", "아이랜드", "웰컴투동막골",
            "헬보이", "백투더퓨처"};
    String[] genre={"드라마","드라마","공포물","드라마","액션","사극","판타지","드라마","마블","판타지"};
    String[] director={"홍길동","한재현","이현지","이만세","강나래","박재훈","김소연","고명수","노희정","박경희"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.VERTICAL, false
        );
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MovieAdapter();
        for(int i=0; i<resId.length; i++){
            adapter.addItem(new Movie(title[i],genre[i],director[i],resId[i]));
        }
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClick(MovieAdapter.ViewHolder holder, View view, int position) {
                Movie item=adapter.getItem(position);
                Toast.makeText(getApplicationContext(),"영화제목: "+item.getTitle(),Toast.LENGTH_LONG).show();
            }
        });
    }
}