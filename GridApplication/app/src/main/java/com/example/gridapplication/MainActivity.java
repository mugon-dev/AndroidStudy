package com.example.gridapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button[] btn=new Button[16];
    int[] ids={R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,
            R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,
            R.id.btn9,R.id.btnAdd,R.id.btnSub,R.id.btnMul,
            R.id.btnDiv,R.id.btnCal, R.id.btnCE
    };
    String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.textView);

        for(int i=0; i<btn.length;i++){
            final int idx=i;
            btn[i]=findViewById(ids[i]);
            btn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String btnStr=btn[idx].getText().toString();
                    if(btnStr.equals("CE")){
                        str="";
                    }else if(btnStr.equals("=")){ //123,-,4
                        StringTokenizer st=new StringTokenizer(str,"+-*/",true);
                        double num1=Double.parseDouble(st.nextToken());
                        String op=st.nextToken();
                        double num2=Double.parseDouble(st.nextToken());
                        double result=0;
                        switch (op){
                            case "+":result=num1+num2;break;
                            case "-":result=num1-num2;break;
                            case "*":result=num1*num2;break;
                            case "/":result=num1/num2;break;
                        }
                        str=Double.toString(result);
                    }else{
                        str+=btnStr;
                    }
                    tv.setText(str);
                }
            });

        }
    }
}