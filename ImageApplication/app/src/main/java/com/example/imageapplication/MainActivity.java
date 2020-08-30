package com.example.imageapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new ImageProView(this));
    }
    public static class ImageProView extends View {
        public ImageProView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            Bitmap pic = BitmapFactory.decodeResource(getResources(),
                    R.drawable.lena256);
            Paint paint = new Paint();
            //블러
            /*int picX = (this.getWidth()-pic.getWidth())/2;
            int picY = (this.getHeight()-pic.getHeight())/2;
            BlurMaskFilter bMask = new BlurMaskFilter(30,BlurMaskFilter.Blur.SOLID);
            canvas.drawBitmap(pic,picX,picY,paint);
            pic.recycle();*/

            //엠보싱
            /*paint.setColor(Color.GRAY);
            int cenX = this.getWidth()/2;
            int cenY = this.getHeight()/2;
            EmbossMaskFilter eMask = new EmbossMaskFilter(new float[]{10,3,3}*//*빛의 방향*//*,0.5f*//*빛의 세기*//*,5,10);
            paint.setMaskFilter(eMask);
            canvas.drawCircle(cenX,cenY,150,paint);*/

            //컬러매트릭스
            int picX = (this.getWidth()-pic.getWidth())/2;
            int picY = (this.getHeight()-pic.getHeight())/2;
            float[] array = {
                    2, 0, 0, 0, -25,
                    0, 2, 0, 0, -25,
                    0, 0, 2, 0, -25,
                    0, 0, 0, 1, -25,
            };
            ColorMatrix cm = new ColorMatrix(array);
            paint.setColorFilter(new ColorMatrixColorFilter(cm));
            canvas.drawBitmap(pic,picX,picY,paint);
            pic.recycle();
        }
    }
}