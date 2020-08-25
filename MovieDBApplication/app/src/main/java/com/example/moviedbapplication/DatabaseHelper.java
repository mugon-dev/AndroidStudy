package com.example.moviedbapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "movie.db";
    public static final int VERSION=1;
    public DatabaseHelper(@Nullable Context context) {
        super(context, DBNAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        printLog("onCreate() 호출");
        String sql = "create table if not exists movie("
                +"_id integer primary key autoincrement,"
                +"title text,"
                +"genre text,"
                +"director text,"
                +"year int,"
                +"actor text,"
                +"content text,"
                +"point double default 0.0,"
                +"count integer default 0)";
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        printLog("onOpen() 호출");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        printLog(oldVersion+"->"+newVersion);
        if(newVersion>1){
            sqLiteDatabase.execSQL("drop table if exists movie");
        }


    }
    //로그 찍을 메소드
    private void printLog(String data){
        Log.d("DataBaseHelper",data);
    }
}
