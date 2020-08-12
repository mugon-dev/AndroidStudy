package com.example.dlgapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        tv=findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] versionArray = {"오레오","파이","큐(10)"};
                final boolean[] checkArray={true,false,false};
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("제목입니다.");
                //dlg.setMessage("이곳은 메시지입니다.");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setMultiChoiceItems(versionArray, checkArray, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which, boolean b) {
                        String str="";
                        for(int i=0; i<checkArray.length; i++){
                            if(checkArray[i]){
                                str += versionArray[i];
                            }
                            tv.setText(str);
                        }
                    }
                });
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });
    }
}