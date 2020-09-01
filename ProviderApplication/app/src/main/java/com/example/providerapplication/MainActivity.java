package com.example.providerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnSelect, btnUpdate, btnDelete;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDelete = findViewById(R.id.btnDelete);
        btnSelect = findViewById(R.id.btnSelect);
        btnInsert = findViewById(R.id.btnInsert);
        btnUpdate = findViewById(R.id.btnUpdate);
        tvResult = findViewById(R.id.tvResult);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPerson();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectPerson();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }
    private void deletePerson(){
        println("deletePerson() 호출");
        String uriString = "content://com.example.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        String selection = "name=?";
        String[] selectionArgs = new String[]{"kim"};
        int count = getContentResolver().delete(uri,selection,selectionArgs);
        println("delete 결과: "+count);
    }

    private void updatePerson() {
        println("updatePerson() 호출");
        String uriString = "content://com.example.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        String selection = "name=?";
        String[] selectionArgs = new String[]{"kim"};
        ContentValues values = new ContentValues();
        values.put("mobile","010-10000-20000");
        int count = getContentResolver().update(uri,values,selection,selectionArgs);
        println("update 결과: "+count);
    }

    private void selectPerson() {
        println("selectPerson() 호출");
        try{
            /*요청 uri 생성*/
            String uriString = "content://com.example.provider/person";
            Uri uri = new Uri.Builder().build().parse(uriString);
            String[] columns = new String[]{"name","age","mobile"};
            Cursor cursor = getContentResolver().query(uri,columns,null,null,"name ASC");
            println("query 결과 : "+cursor.getCount());
            int index = 0;
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(columns[0]));
                int age = cursor.getInt(cursor.getColumnIndex(columns[1]));
                String mobile = cursor.getString(cursor.getColumnIndex(columns[2]));
                println("#"+index+"->"+name+" , "+age+" , "+mobile);
                index++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void insertPerson() {
        println("insertPerson() 호출");
        /*요청 uri 생성*/
        String uriString = "content://com.example.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriString);
        Cursor cursor = getContentResolver().query(uri,null,null,null,null);
        String[] columns = cursor.getColumnNames();
        println("columns count -> "+columns.length);
        for (int i = 0; i < columns.length; i++) {
            println("#"+i+" : "+columns[i]);
        }
        ContentValues values = new ContentValues();
        values.put("name","kim");
        values.put("age",20);
        values.put("mobile","010-1111-2222");
        uri = getContentResolver().insert(uri,values);
        println("insert 결과: "+uri.toString());
    }
    private void println(String data){
        tvResult.append(data+"\n");
    }
}