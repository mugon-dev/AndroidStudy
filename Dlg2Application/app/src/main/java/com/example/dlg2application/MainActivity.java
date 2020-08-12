package com.example.dlg2application;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvName, tvEmail;
    Button btn1;
    EditText dlgEdName, dlgEdEmail;
    TextView toastText;
    View dialogView, toastView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);
        btn1=findViewById(R.id.button);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //layout에 있는 것을 메모리에 올리기 위해서는 inflate
                dialogView=View.inflate(MainActivity.this,R.layout.dlg1,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자정보입력");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dlgEdName=dialogView.findViewById(R.id.editName);
                        dlgEdEmail=dialogView.findViewById(R.id.editEmail);
                        tvName.setText(dlgEdName.getText().toString());
                        tvEmail.setText(dlgEdEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast=new Toast(MainActivity.this);
                        toastView = View.inflate(MainActivity.this,R.layout.toast,null);
                        toastText=toastView.findViewById(R.id.textView3);
                        toastText.setText("취소했습니다.");
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });
    }
}