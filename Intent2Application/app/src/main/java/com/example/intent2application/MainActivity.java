package com.example.intent2application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edNum1,edNum2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edNum1=findViewById(R.id.edNumber1);
        edNum2=findViewById(R.id.edNumber2);
        btn=findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra("num1",Integer.parseInt(edNum1.getText().toString()));
                intent.putExtra("num2",Integer.parseInt(edNum2.getText().toString()));
                //새로 연 액티비티에 값 전달할때 돌아오는 주소
                startActivityForResult(intent,100);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == 100) && (resultCode == RESULT_OK)) {
            int sum = data.getIntExtra("sum", 0);
            Toast.makeText(getApplicationContext(),"합계: "+sum,Toast.LENGTH_LONG).show();
        }
    }
}