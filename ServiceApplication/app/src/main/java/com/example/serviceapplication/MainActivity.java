package com.example.serviceapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed.getText().toString();
                Intent intent = new Intent(
                        getApplicationContext(), MyService.class
                );
                intent.putExtra("command","Service show");
                intent.putExtra("name",name);
                //만든 서비스 호출
                startService(intent);
            }
        });
        //메인페이지로 다시 올때
        Intent passIntent = getIntent();
        processIntent(passIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent!=null){
            String command = intent.getStringExtra("command");
            String name = intent.getStringExtra("name");
            Toast.makeText(this,"command: "+command+"name: "+name,Toast.LENGTH_LONG).show();
        }
    }
}