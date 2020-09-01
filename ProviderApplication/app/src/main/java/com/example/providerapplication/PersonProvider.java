package com.example.providerapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class PersonProvider extends ContentProvider {
    //현재 제공자
    private static final String AUTHORITY = "com.example.provider";
    //데이터 타입
    private static final String BASE_PATH = "person";
    public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+BASE_PATH);
    private static final int PERSONS = 1;
    private static final int PERSON_ID = 2;

    //select등의 쿼리를 사용할때마다 uri가 맞는지 확인
   private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(AUTHORITY,BASE_PATH,PERSONS);
        uriMatcher.addURI(AUTHORITY,BASE_PATH+"/#",PERSON_ID);
    }

    private SQLiteDatabase database;

    public PersonProvider() {

    }

    @Override
    public int delete(Uri uri, String selection/*where 조건*/, String[] selectionArgs/*where조건 값= (?값)*/) {
        // Implement this to handle requests to delete one or more rows.
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.delete(DatabaseHelper.TABLE_NAME,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("알수없는 URI: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count; /*삭제된 데이터 개수*/
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        // throw new UnsupportedOperationException("Not yet implemented");
        switch (uriMatcher.match(uri)){
            case PERSONS:
                return "vnd.android.cursor.dir/persons";
            default:
                throw new IllegalArgumentException("알 수 없는 URI: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri/*요청할때 사용하는 uri*/, ContentValues values /*values에 데이터 담아옴*/) {
        // TODO: Implement this to handle requests to insert a new row.
//        throw new UnsupportedOperationException("Not yet implemented");
        long id = database.insert(DatabaseHelper.TABLE_NAME,null /*안들어온 데이터 처리방법*/,values);
        if(id>0 /*정상적으로 insert가 되면*/){
            Uri _uri = ContentUris.withAppendedId(CONTENT_URI,id);
            getContext().getContentResolver().notifyChange(uri,null); //지금 현재 uri 내용이 바뀜을 알려줌
            return _uri; //정상적으로 처리된 uri 리턴
        }
        throw new SQLException("추가 실패 -> URI : "+uri);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        DatabaseHelper helper = new DatabaseHelper(getContext());
        database = helper.getWritableDatabase();
        return true;
    }

    //Select
    @Override
    public Cursor query(Uri uri, String[] projection/*칼럼*/, String selection/*(where을 제외한 where절)*/,
                        String[] selectionArgs/*WHERE문에 전달해줄 값들*/, String sortOrder/*오름차순내림차순*/) {
        // TODO: Implement this to handle query requests from clients.
        // throw new UnsupportedOperationException("Not yet implemented");
        Cursor cursor = null;
        switch (uriMatcher.match(uri)/*받아온 uri가 무엇인가에 따라 작업*/){
            case PERSONS :
                cursor = database.query(DatabaseHelper.TABLE_NAME,DatabaseHelper.ALL_COLUMNS,selection,null,null,null,DatabaseHelper.PERSON_NAME+" ASC" );
                break;
            default:
                throw new IllegalArgumentException("알수없는 URI "+uri);
        }
        //uri를 resolver형태로 cursor에 리턴
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values/*set의 내용 (필드명=업데이트 내용)*/, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        // throw new UnsupportedOperationException("Not yet implemented");
        int count = 0;
        switch (uriMatcher.match(uri)){
            case PERSONS:
                count = database.update(DatabaseHelper.TABLE_NAME,values,selection,selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("알 수 없는 URI: "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return count;
    }
}
