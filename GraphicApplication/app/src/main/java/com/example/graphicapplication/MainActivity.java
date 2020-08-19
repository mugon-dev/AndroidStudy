package com.example.graphicapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyGraphicView(this));
    }
    private static class MyGraphicView extends View {

        public MyGraphicView(Context context) {
            super(context);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN: break;
                case MotionEvent.ACTION_UP: break;
                case MotionEvent.ACTION_MOVE: break;
                case MotionEvent.ACTION_CANCEL: break;
            }
            return true;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.GREEN);
            canvas.drawLine(10,10,300,10,paint/*그림그리는 도구*/);
            paint.setColor(Color.BLUE);
            paint.setStrokeWidth(10);//선의 굵기
            canvas.drawLine(10,30,300,30,paint);

            paint.setColor(Color.RED);
            paint.setStrokeWidth(0);
            paint.setStyle(Paint.Style.FILL);
            Rect r1 = new Rect(10,50,10+100,50+100); //원그리기 첫번째 봐표
            canvas.drawRect(r1,paint);

            paint.setStyle(Paint.Style.STROKE);
            Rect r2 = new Rect(130,50,130+100,50+100);
            canvas.drawRect(r2,paint);

            RectF r3 = new RectF(250,50,250+100,50+100);
            canvas.drawRoundRect(r3,20,20,paint);

            canvas.drawCircle(50,220,50,paint);
            paint.setStrokeWidth(5);
            Path path =new Path();
            path.moveTo(20,290);
            path.lineTo(10+50,290+50);
            path.lineTo(10+100,290);
            path.lineTo(10+150,290+50);
            path.lineTo(10+200,290);

            paint.setStrokeWidth(0);
            paint.setTextSize(30);
            canvas.drawText("안드로이드",10,390,paint);


        }
    }
}