package com.example.moviedbapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public class MovieListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    MovieAdapter adapter;
    DatabaseHelper helper;
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new MovieAdapter();
        //selectData();
        //recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void OnItemClick(MovieAdapter.ViewHolder holder, View view, int position) {
                Movie movie=adapter.items.get(position);
                Intent intent=new Intent(getApplicationContext(),
                        MovieContentActivity.class);
                intent.putExtra("movie",movie);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.items.clear();
        selectData();
        recyclerView.setAdapter(adapter);
    }

    private void selectData(){
        helper=new DatabaseHelper(this);
        database=helper.getReadableDatabase();
        String sql="select * from movie";
        Cursor cursor=database.rawQuery(sql,null);
        int count=cursor.getCount();
        for(int i=0;i<count;i++){
            cursor.moveToNext();
            Movie movie=new Movie(
                    cursor.getInt(0), //id
                    cursor.getString(1), //title
                    cursor.getString(2), //genre
                    cursor.getString(3), //director
                    cursor.getInt(4), //year
                    cursor.getString(5), //actor
                    cursor.getString(6), // content
                    cursor.getDouble(7), // point
                    cursor.getInt(8));  //count
            adapter.addItem(movie);
        }
        adapter.notifyDataSetChanged();

    }
}