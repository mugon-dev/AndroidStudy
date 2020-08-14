package com.example.intentexapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class InputActivity extends AppCompatActivity {
    EditText edId,edPw,edNAme,edPhone;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        edId=findViewById(R.id.edId);
        edPw=findViewById(R.id.edPw);
        edPhone=findViewById(R.id.edPhone);
        edNAme=findViewById(R.id.edName);
        btnSave=findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edId.getText().toString();
                String pw = edPw.getText().toString();
                String name = edNAme.getText().toString();
                int phone = Integer.parseInt(edPhone.getText().toString());
                Member member = new Member(id,pw,name,phone);
                Intent intent = new Intent(InputActivity.this,MainActivity.class);
                intent.putExtra("member",member);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}