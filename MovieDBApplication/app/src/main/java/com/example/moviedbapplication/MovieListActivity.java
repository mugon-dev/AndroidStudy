package com.example.moviedbapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MovieListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MovieAdapter adapter;
    SQLiteDatabase database;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MovieAdapter();
        recyclerView.setAdapter(adapter);
    }
    private void selectData(){
        helper=new DatabaseHelper(this);
        database=helper.getReadableDatabase(); //읽기만 가능
        String sql="select * from movie";
        Cursor cursor = database.rawQuery(sql,null  );
        int count = cursor.getCount();
        for(int i=0; i<count; i++){
            cursor.moveToNext();
            Movie movie = new Movie(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getDouble(7),
                    cursor.getInt(8)
            );
            adapter.items.add(movie);
        }
    }
}