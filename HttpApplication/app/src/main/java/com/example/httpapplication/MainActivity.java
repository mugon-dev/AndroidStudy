package com.example.httpapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView tv;
    Button btn;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        ed=findViewById(R.id.edit1);
        tv=findViewById(R.id.textView);
        btn=findViewById(R.id.button);
        ed.setText("https://movie.naver.com/movie/running/current.nhn");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String strUrl = ed.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        request(strUrl);
                    }
                }).start();
            }
        });
    }
    private void request(String strUrl){
        //String에 데이터를 계속 연결하면 이전 객체가 지워지지 않기때문에 가비지 발생 = 딜레이요소
        StringBuilder output = new StringBuilder();
        try{
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            if(conn != null){
                conn.setConnectTimeout(100000); //10초간 연결 시도 후 안되면 timeout
                conn.setRequestMethod("GET"); //get 형식 요청
                conn.setDoInput(true); //입력받는 형태로 페이지 오픈
                int resCode = conn.getResponseCode();
                //주소로 연결된 페이지 읽어오기
                //버퍼에 넣으면 한 라인씩 읽음
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = null;
                while(true){
                    //한줄씩 읽어와서 라인에 저장
                    line = reader.readLine();
                    if(line==null){
                        break;
                    }
                    //stringbuild에 라인 붙이기
                    output.append(line+"\n");
                }
                reader.close();
                conn.disconnect();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        printTv("응답 ->"+output);
    }
    private void printTv(final String data){
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(data);
            }
        });
    }
}