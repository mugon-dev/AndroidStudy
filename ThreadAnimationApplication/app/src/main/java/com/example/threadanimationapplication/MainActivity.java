package com.example.threadanimationapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    Button btnStart, btnStop;
    ArrayList<Drawable> drawList = new ArrayList<>();
    Handler handler;
    AnimThread thread;
    AnimTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.imageView);
        btnStart = findViewById(R.id.button1);
        btnStop = findViewById(R.id.button2);
        handler = new Handler();
        Resources res = getResources(); //리소스 객체 생성
        drawList.add(res.getDrawable(R.drawable.face1));
        drawList.add(res.getDrawable(R.drawable.face2));
        drawList.add(res.getDrawable(R.drawable.face3));
        drawList.add(res.getDrawable(R.drawable.face4));
        drawList.add(res.getDrawable(R.drawable.face5));
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*thread = new AnimThread();
                thread.start();*/
                task = new AnimTask();
                task.execute();
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*thread.interrupt();*/
                task.cancel(true);

            }
        });
    }
    class AnimTask extends AsyncTask<Integer,Integer,Integer>{
        int index;
        @Override
        protected void onPreExecute() {
            index = 0;
            iv.setImageDrawable(drawList.get(index));
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(isCancelled()){
                index++;
                if(index>5){
                    index=0;
                }
                publishProgress(index);
                try{
                    Thread.sleep(200);
                }catch (Exception e){}
            }
            return index;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            iv.setImageDrawable(drawList.get(0));
        }

        @Override
        protected void onPostExecute(Integer integer) {
            iv.setImageDrawable(drawList.get(integer));
        }

        @Override
        protected void onCancelled() {
            iv.setImageDrawable(drawList.get(0));
        }
    }

    class AnimThread extends Thread {
        @Override
        public void run() {
            int index = 0;
            while (true) {
                final Drawable drawable = drawList.get(index);
                index++;
                if (index > 5) {
                    index = 0;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iv.setImageDrawable(drawable);
                    }
                });
                try{
                    Thread.sleep(200);
                }catch (InterruptedException e){
                    return;
                }
            }
        }
    }
}