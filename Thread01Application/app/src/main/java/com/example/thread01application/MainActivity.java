package com.example.thread01application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText et;
    Button btn;
    /*MainHandler handler;*/
    Handler handler = new Handler();
    int value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.edit1);
        btn = findViewById(R.id.button);
        /*handler = new MainHandler();*/

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });
    }

    class BackgroundThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                value += 1;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        et.setText("value: " + value);
                    }
                });
            }
        }
    }
}

    /*class BackgroundThread extends Thread {
        @Override
        public void run() {
            int value = 0;
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                value += 1;
                Log.i("Thread1: ", "value: " + value);
                //스레드 안에서는 메인 액티비티의 요소(UI)에 접근 불가
                //et.setText("value: "+value);
                //핸들러 형태의 메시지 객체만들기
                Message msg = handler.obtainMessage();
                //보낼 데이터를 번들 객체에 넣기
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                //메시지 객체에 번들 객체 넣기
                msg.setData(bundle);
                //핸들러로 메시지 객체 보내기
                handler.sendMessage(msg);
            }
        }
    }*/

   /* class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            int value = bundle.getInt("value");
            et.setText("value: " + value);
        }
    }
}*/