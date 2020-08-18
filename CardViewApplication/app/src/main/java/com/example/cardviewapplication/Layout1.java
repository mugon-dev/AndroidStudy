package com.example.cardviewapplication;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Layout1 extends LinearLayout {
    ImageView imageView;
    TextView tvName, tvPhone;

    public Layout1(Context context) { //레이아웃 파일이 없을때
        super(context);
        init(context);
    }

    public Layout1(Context context, @Nullable AttributeSet attrs) { //레이아웃 파일이 있을때
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout1, this, true); //xml의 내용이 메모리에 적재
        imageView = findViewById(R.id.iv);
        tvName = findViewById(R.id.tvName);
        tvPhone = findViewById(R.id.tvPhone);
    }
    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
    public void setName(String name){
        tvName.setText(name);
    }
    public void setPhone(String mobile){
        tvPhone.setText(mobile);
    }
}
