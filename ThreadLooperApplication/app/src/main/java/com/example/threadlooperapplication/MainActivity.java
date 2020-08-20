package com.example.threadlooperapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView tv;
    Button btn;
    Handler handler = new Handler();
    ProcessThread thread = new ProcessThread();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.edit1);
        tv = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ed 문자가져오기
                String str = ed.getText().toString();
                //메시지 객체 생성
                Message msg = Message.obtain();
                msg.obj = str;
                thread.processHandler.sendMessage(msg);
            }
        });
    }

    class ProcessThread extends Thread {
        ProcessHandler processHandler = new ProcessHandler();
        @Override
        public void run() {
            //들어오는 메시지 감시,검사
            Looper.prepare();
            Looper.loop();
        }
        class ProcessHandler extends Handler{
            //메인에서 메시지 가져오기
            @Override
            public void handleMessage(@NonNull Message msg) {
                final String output = msg.obj+" from MainThread";
                Log.i("Looper Test", output);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(output);
                    }
                });
            }
        }
    }

}