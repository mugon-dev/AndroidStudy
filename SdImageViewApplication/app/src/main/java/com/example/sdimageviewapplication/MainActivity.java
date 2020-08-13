package com.example.sdimageviewapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button btnPrev, btnNext;
    MyPictureView myPictureView;
    int curNum;
    File[] imageFiles;
    String imageFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("이미지 뷰어");
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btnNext=findViewById(R.id.btnNext);
        btnPrev=findViewById(R.id.btnPrev);
        myPictureView=findViewById(R.id.myPictureView1);
        imageFiles=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures").listFiles();
        imageFileName=imageFiles[0].toString();
        myPictureView.imagePath=imageFileName;
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum<=0){
                    Toast.makeText(getApplicationContext(),"첫번째그림입니다.",Toast.LENGTH_SHORT).show();
                }else {
                    curNum--;
                    imageFileName=imageFiles[curNum].toString();
                    myPictureView.imagePath=imageFileName;
                    myPictureView.invalidate();
                }
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(curNum>=imageFiles.length-1){
                    Toast.makeText(getApplicationContext(),"마지막 그림입니다.",Toast.LENGTH_SHORT).show();
                }else {
                    curNum++;
                    imageFileName=imageFiles[curNum].toString();
                    myPictureView.imagePath=imageFileName;
                    myPictureView.invalidate();
                }
            }
        });

    }
}