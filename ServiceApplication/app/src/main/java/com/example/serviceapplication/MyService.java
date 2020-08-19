package com.example.serviceapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public static final String TAG = "MyService";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate 메소드 호출");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy 메소드 호출");
    }

    //서비스 호출
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand 메소드 호출");

        if (intent == null) {
            return Service.START_STICKY;
        } else {
            processCommand(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    //실제 작업
    private void processCommand(Intent intent) {
        //메인 액티비티에서 인텐트에 메시지 담아 옴
        String command = intent.getStringExtra("command");
        String name = intent.getStringExtra("name");
        Log.d(TAG, "command: " + command + "name: " + name);
        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000); //10초 휴식
            } catch (Exception e) {
                Log.d(TAG, "Waiting" + i + "seconds.");
            }
        }
        //메인 액티비티에 메시지 전송
        Intent showIntent = new Intent(
                getApplicationContext(), MainActivity.class
        );
        //같은(쓸데없는) 액티비티가 쌓이는 것을 방지
        showIntent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK |
                intent.FLAG_ACTIVITY_SINGLE_TOP |
                intent.FLAG_ACTIVITY_CLEAR_TOP);
        showIntent.putExtra("command","show");
        showIntent.putExtra("name",name+" from service");
        startActivity(showIntent);
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
