package com.example.moviedbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MovieContentActivity extends AppCompatActivity {
    TextView tvTitle,tvGenre,tvDirector,tvYear,tvActor,tvContent;
    RatingBar ratingBar;
    Button btn;
    Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_content);
        Intent intent=getIntent();
        movie=(Movie)intent.getSerializableExtra("movie");

        tvTitle=findViewById(R.id.tvTitle);
        tvGenre=findViewById(R.id.tvGenre);
        tvDirector=findViewById(R.id.tvDirector);
        tvYear=findViewById(R.id.tvYear);
        tvActor=findViewById(R.id.tvActor);
        tvContent=findViewById(R.id.tvContent);
        tvTitle.setText(movie.getTitle());
        tvGenre.setText(movie.getGenre());
        tvDirector.setText(movie.getDirector());
        tvYear.setText(movie.getYear()+"년");
        tvActor.setText(movie.getActor());
        tvContent.setText(movie.getContent());

        ratingBar=findViewById(R.id.ratingBar);
        btn=findViewById(R.id.btnPoint);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=movie.getTitle();

                int count=movie.getCount()+1;
                double point=movie.getPoint()+ratingBar.getRating();

                printLog(point+"");
                setMoviePoint(title, point,count);
            }
        });

    }
    private void setMoviePoint(String title, double point,int count){

        DatabaseHelper helper=new DatabaseHelper(this);
        SQLiteDatabase database=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("point",point);
        String sql="update movie set point="+point+",count="+count+" where title='"+title+"'";

        database.execSQL(sql);
        finish();
    }
    private void printLog(String data){
        Log.d("POINTER",data);
    }
}