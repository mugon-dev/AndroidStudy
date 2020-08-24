package com.example.moviedbapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    EditText edTitle, edGenre, edDirector, edYear, edActor, edContent;
    Button btnSave, btnCancel;
    SQLiteDatabase database;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        edTitle=findViewById(R.id.editTitle);
        edGenre=findViewById(R.id.editGenre);
        edDirector=findViewById(R.id.editDirector);
        edYear=findViewById(R.id.editYear);
        edActor=findViewById(R.id.editActor);
        edContent=findViewById(R.id.edContent);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
    }
    private void insertData(){
        helper = new DatabaseHelper(this);
        database=helper.getWritableDatabase();
        String title=edTitle.getText().toString();
        String genre=edGenre.getText().toString();
        String director=edDirector.getText().toString();
        int year = Integer.parseInt(edYear.getText().toString());
        String actor = edActor.getText().toString();
        String content = edContent.getText().toString();
        String sql="insert into movie(title,genre,director,year,actor,content) values('"+
                title+"','"+genre+"','"+director+"',"+year+",'"+actor+"','"+content+"')";
        database.execSQL(sql);
        init();
        printLog("데이터입력");
    }
    private void init(){
        edTitle.setText("");
        edGenre.setText("");
        edDirector.setText("");
        edYear.setText("");
        edActor.setText("");
        edContent.setText("");
    }

    private void printLog(String data) {
        Log.d("InputActivity",data);
    }
}