package com.example.database01application;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edDb, edTable;
    Button btnDB, btnTable, btnInsert, btnSelect, btnUpdate, btnDelete;
    TextView tv;
    SQLiteDatabase database;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edDb=findViewById(R.id.editDB);
        edTable = findViewById(R.id.editTable);
        btnDB = findViewById(R.id.btnDB);
        btnTable = findViewById(R.id.btnTable);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSearch);
        tv = findViewById(R.id.tvResult);

        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dbName = edDb.getText().toString();
                createDatabase(dbName);
            }
        });
        btnTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tableName = edTable.getText().toString();
                createTable();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectData();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
    }
    private void selectData(){
        println("selectData() 호출");
        String sql="select * from "+tableName;
        Cursor cursor = database.rawQuery(sql,null);
        int count = cursor.getCount();
        for (int i = 0; i < count; i++) {
            cursor.moveToNext();
            int id=cursor.getInt(1);
            String name = cursor.getString(2);
            int age = cursor.getInt(3);
            String mobile = cursor.getString(4);
            println("레코드#: "+i+":"+id+","+name+","+age+","+mobile);
        }
        cursor.close();
    }
    private void insertData(){
        println("insertData() 호출");
        if(database==null){
            println("데이터베이스 먼저 생성");
            return;
        }
        if(tableName==null){
            println("테이블을 먼저 생성");
            return;
        }
        String sql="insert into "+tableName+"(name,age,mobile) values('홍길동','20','010-1111-2222')";
        database.execSQL(sql);
        println("레코드를 추가함");
    }
    private void createDatabase(String dbName){
        println("createDatabase()호출됨");
        //데이터 베이스가 있으면 오픈 없으면 생성
        database=openOrCreateDatabase(dbName,MODE_PRIVATE,null);
        println("데이터 베이스 생성함: "+dbName);
    }
    private void createTable(){
        println("createTable() 호출됨");
        if(database==null){
            println("데이터베이스를 먼저 생성");
            return;
        }
        //_id 는 오라클에서 자동으로 생성되는 아이디
        String sql="create table if not exists "+tableName+"("
                +"_id integer PRIMARY KEY autoincrement,"
                +"name text,"
                +"age integer,"
                +"mobile text)";
        database.execSQL(sql);
        println("테이블 생성함: "+tableName);
    }
    private void println(String data){
        tv.append(data+"\n");
    }
}