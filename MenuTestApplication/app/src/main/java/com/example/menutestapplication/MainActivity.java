package com.example.menutestapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    EditText edNum;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("그림 회전");
        linearLayout=findViewById(R.id.linerLayout1);
        edNum=findViewById(R.id.edNum);
        imageView=findViewById(R.id.imageView);
        imageView.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.mov01));
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.item2:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.mov02));
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.item3:
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.mov03));
                imageView.setVisibility(View.VISIBLE);
                break;
            case R.id.btnRotate:
                imageView.setRotation(Integer.parseInt(edNum.getText().toString()));
                break;
        }
        return true;
    }
}