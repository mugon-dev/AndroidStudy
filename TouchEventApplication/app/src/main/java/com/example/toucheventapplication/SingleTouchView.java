package com.example.toucheventapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class SingleTouchView extends View {
    private Paint paint = new Paint();
    private Path path = new Path();
    public SingleTouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10f);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX,eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                 path.lineTo(eventX,eventY);
                 break;
            case MotionEvent.ACTION_UP:
                 break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if(event.getAction()==KeyEvent.ACTION_UP){
            if (keyCode==KeyEvent.KEYCODE_W){
                paint.setColor((Color.WHITE));
            }else if(keyCode==KeyEvent.KEYCODE_R){
                paint.setColor((Color.RED));
            }
            invalidate();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }
}
