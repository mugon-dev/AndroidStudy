

package com.example.socketapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView tvClient, tvServer;
    Button btnSend , btnServer;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        editText = findViewById(R.id.edit);
        tvClient = findViewById(R.id.tvClient);
        tvServer = findViewById(R.id.tvServer);
        btnSend = findViewById(R.id.btnSend);
        btnServer = findViewById(R.id.btnServer);

        btnServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        startServer();
                    }
                }).start();
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String data = editText.getText().toString();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        send(data);
                    }
                }).start();
            }
        });
    }
    private void startServer() {
        try {
            int portNum = 5001;
            //서버 소켓 생성
            ServerSocket serverSocket = new ServerSocket(portNum);
            printServerLog("서버 시작함: "+portNum);

            while(true){
                //클라이언트로부터 요청이 오면 수락하고 소켓 생성
                Socket socket = serverSocket.accept();
                InetAddress clientHost = socket.getLocalAddress();
                int clientPort = socket.getPort();
                printServerLog("클라이언트 연결됨 : "+clientHost+" : "+clientPort);
                //socket으로 받은 inputStream을 object형으로 변환
                ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
                Object obj = is.readObject();
                printServerLog("데이터 받음: "+obj);
                ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
                os.writeObject(obj+" from Server.");
                os.flush();
                printServerLog("데이터 보냄");
                socket.close();
            }
        } catch (Exception e) {
        }
    }
    private void send(String data){
        try{
            int portNUm = 5001;
            Socket socket = new Socket("localhost",portNUm);
            printClientLog("소켓연결함");
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(data);
            os.flush();
            printClientLog("데이터 전송함");

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            printClientLog("서버로부터 받음: "+is.readObject());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //final -> Runnable 함수 안에까지 넣기 위해
    private void printServerLog(final String data) {
        Log.d("MainActivity",data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                //set은 하나만 가능 , append는 여러 데이터
                tvServer.append(data + "\n");
            }
        });
    }
    private void printClientLog(final String data) {
        Log.d("MainActivity",data);
        handler.post(new Runnable() {
            @Override
            public void run() {
                tvClient.append(data + "\n");
            }
        });
    }

}