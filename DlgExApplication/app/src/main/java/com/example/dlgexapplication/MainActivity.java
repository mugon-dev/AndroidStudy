package com.example.dlgexapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    RadioGroup rdGroup;
    Button btn;
    View dialogView, toastView;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rdGroup=findViewById(R.id.rdGroup);
        btn=findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title="";
                int drawable=0;
                int id=rdGroup.getCheckedRadioButtonId();
                switch (id){
                    case R.id.rdDog:
                        title="강아지";
                        drawable = R.drawable.dog2;
                        //iv.setImageDrawable(getResources().getDrawable(R.drawable.dog2));
                        break;
                    case R.id.rbCat:
                        title="고양이";
                        drawable = R.drawable.cat;
                        //iv.setImageDrawable(getResources().getDrawable(R.drawable.cat));
                        break;
                    case R.id.rbRabbit:
                        title="토끼";
                        drawable = R.drawable.rabbit;
                        //iv.setImageDrawable(getResources().getDrawable(R.drawable.rabbit));
                }
                dialogView=View.inflate(MainActivity.this,R.layout.dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                iv=dialogView.findViewById(R.id.iv);
                iv.setImageResource(drawable);
                dlg.setTitle(title);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",null);
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = View.inflate(MainActivity.this,R.layout.toast,null);
                        toastView=toastView.findViewById(R.id.textView2);
                        toast.show();
                    }
                });
                dlg.show();

            }
        });
    }
}