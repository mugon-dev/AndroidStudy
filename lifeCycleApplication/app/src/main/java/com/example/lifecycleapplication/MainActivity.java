package com.example.lifecycleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNew, btnFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("메인 액티비티 테스으: ","onCreate()");
        btnNew=findViewById(R.id.btnOpenNew);
        btnFinish=findViewById(R.id.btnFinish);
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("메인 액티비티 테스으: ","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("메인 액티비티 테스으: ","onResume()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("메인 액티비티 테스으: ","onRestart()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("메인 액티비티 테스으: ","onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("메인 액티비티 테스으: ","onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("메인 액티비티 테스으: ","onDestroy()");

    }
}