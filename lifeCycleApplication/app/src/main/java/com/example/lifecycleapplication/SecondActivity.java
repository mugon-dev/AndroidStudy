package com.example.lifecycleapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i("second 액티비티 테스트: ","onCreate()");

        btn=findViewById(R.id.btnFinish_s);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("second 액티비티 테스트: ","onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("second 액티비티 테스트: ","onResume()");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("second 액티비티 테스트: ","onRestart()");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("second 액티비티 테스트: ","onPause()");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("second 액티비티 테스트: ","onStop()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("second 액티비티 테스트: ","onDestroy()");

    }
}