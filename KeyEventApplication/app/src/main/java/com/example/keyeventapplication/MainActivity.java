package com.example.keyeventapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    int x = 100, y = 100;
    protected class MyView extends View {

        public MyView(Context context) {
            super(context);
            setBackgroundColor(Color.YELLOW);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            Paint paint = new Paint();
            canvas.drawRect(x,y,x+50,y+50,paint);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        MyView w = new MyView(this);
        //keyEvent할때 focusable 필수
        w.setFocusable(true);
        w.setFocusableInTouchMode(true);
        setContentView(w);
        w.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_UP  ){
                    switch (i){
                        case KeyEvent.KEYCODE_DPAD_LEFT:
                            if(x-30<=0) x=0;
                            else x -= 30;
                            break;
                        case KeyEvent.KEYCODE_DPAD_RIGHT:
                            if(x+30>=view.getWidth()) x=view.getWidth()-50;
                            else x += 30;
                            break;
                        case KeyEvent.KEYCODE_DPAD_UP:
                            if(y-30<=0) y=0;
                            else y -= 30;
                            break;
                        case KeyEvent.KEYCODE_DPAD_DOWN:
                            if(y+30>=view.getHeight()) y=view.getHeight()-50;
                            else y += 30;
                            break;
                    }
                    view.invalidate();
                    return true;
                }
                return false;
            }
        });
    }
}