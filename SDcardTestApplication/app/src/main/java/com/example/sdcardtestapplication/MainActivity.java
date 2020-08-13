package com.example.sdcardtestapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.editTextTextMultiLine);
        btn=findViewById(R.id.button);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sysDir = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures";
                //파일 리스트 저장
                File[] sysFiles = new File(sysDir).listFiles();
                String strFileName;
                for(int i=0; i<sysFiles.length; i++){
                    if(sysFiles[i].isDirectory() == true){
                        strFileName = "<폴더>"+sysFiles[i].toString();
                    }else{
                        strFileName = "<파일>"+sysFiles[i].toString();
                    }
                    ed.setText((ed.getText()+"\n"+strFileName));
//                    ed.append(strFileName+"\n");
                }
            }
        });
    }
}