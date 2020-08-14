package com.example.intentexapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OutputActivity extends AppCompatActivity {
    TextView tvResultId, tvResultPw, tvResultName, tvResultPhone;
    Button btnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        tvResultId=findViewById(R.id.tvResultId);
        tvResultPw=findViewById(R.id.tvResultPw);
        tvResultName=findViewById(R.id.tvResultName);
        tvResultPhone=findViewById(R.id.tvResultPhone);
        btnClose=findViewById(R.id.btnClose);
        Intent intent = getIntent();
        Member member = (Member)intent.getSerializableExtra("member");
        String id = member.getId();
        String pw = member.getPw();
        String name = member.getName();
        int phone = member.getPhone();
        tvResultId.setText(id);
        tvResultPw.setText(pw);
        tvResultName.setText(name);
        tvResultPhone.setText(phone+"");
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}