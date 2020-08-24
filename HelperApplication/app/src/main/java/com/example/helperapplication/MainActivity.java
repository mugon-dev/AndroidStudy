package com.example.helperapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText edName, edAge, edMobile;
    TextView tvResult;
    Button btnDB,btnInsert, btnSelect, btnUpdate, btnDelete;
    DatabaseHelper helper; /*데이터베이스를 오픈하고 새로 만드는 도구*/
    SQLiteDatabase database; /*데이터 베이스 생성*/
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edName = findViewById(R.id.edName);
        edAge = findViewById(R.id.edAge);
        edMobile = findViewById(R.id.edMobile);
        tvResult = findViewById(R.id.tvResult);
        btnInsert = findViewById(R.id.btnInsert);
        btnSelect = findViewById(R.id.btnSelect);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnDB = findViewById(R.id.btnDB);
        btnDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatabase();
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
    }
    private void deleteData(){
        String name = edName.getText().toString();
        String sql="delete from emp hwere name='"+name+"'";
        database.execSQL(sql);
    }
    private void selectData(){
        println("selectData() 호출");
        String sql="select * from emp";
        Cursor cursor = database.rawQuery(sql,null);
        int count = cursor.getCount();
        println("레코드 수: "+count);
        for(int i=0; i<count; i++){
            cursor.moveToNext();
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String mobile = cursor.getString(3);
            println("레코드 #"+i+":"+id+","+name+","+age+","+mobile);
        }
        cursor.close();
    }
    private void updateData(){
        println("updateData() 호출");
        String name = edName.getText().toString();
        String mobile = edMobile.getText().toString();
        String sql="update emp set mobile='"+mobile+"' where name='"+name+"'";
        database.execSQL(sql);
    }
    private void insertData(){
        println("insertData() 호출");
        String name = edName.getText().toString();
        int age = Integer.parseInt(edAge.getText().toString());
        String mobile = edMobile.getText().toString();
        String sql = "insert into emp(name,age,mobile) values('"+name+"',"+age+",'"+mobile+"');";
        database.execSQL(sql);
        edMobile.setText("");
        edAge.setText("");
        edName.setText("");
    }
    private void createDatabase(){
        println("createDatabase() 호출함");
        helper = new DatabaseHelper(this); //호출하면 onCreate호출됨
        database = helper.getWritableDatabase(); //데이터베이스를 쓰기가능한 형태로 열기
        println("데이터베이스 생성");
    }
    private void println(String data){
        tvResult.append(data+"\n");
    }
}