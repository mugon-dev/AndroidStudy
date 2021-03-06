package com.example.oraclejspapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText edId,edPw;
    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edId = findViewById(R.id.editId);
        edPw = findViewById(R.id.editPw);
        tv = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String pw = edPw.getText().toString();
                RegisterTask task = new RegisterTask();
                task.execute(id,pw); //데이터 넘기기
            }
        });
    }
    class RegisterTask extends AsyncTask<String,String,String>{
        String sendMsg, receiverMsg;

        @Override
        protected String doInBackground(String... strings) {
            try{
                String str = "";
                URL url = new URL("http://192.168.0.24:8092/AndroidConn/insertDB");/*연결할 주소*/
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();/*url연결*/
                conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),"UTF-8");
                sendMsg = "id="+strings[0]+"&pw="+strings[1];
                osw.write(sendMsg);
                osw.flush();
                osw.close();
                if(conn.getResponseCode()==conn.HTTP_OK/*접속성공코드*/){
                    InputStreamReader isr = new InputStreamReader(conn.getInputStream(),"UTF-8"); //데이터 읽어오기
                    BufferedReader reader = new BufferedReader(isr); //버퍼에 저장
                    StringBuffer buffer = new StringBuffer();
                    while ((str=reader.readLine())!=null){
                        buffer.append(str);
                    }
                    receiverMsg = buffer.toString();
                }
            }catch (MalformedURLException e){
                e.printStackTrace();
            }catch(IOException e){
                e.printStackTrace();
            }
            return receiverMsg;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv.setText(s);
        }
    }
}