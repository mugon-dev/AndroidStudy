package com.example.votetestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {
    TextView[] tv = new TextView[9];
    RatingBar[] ratingBars = new RatingBar[9];
    Button btn;
    ImageView iv;
    int[] tvIds={R.id.tv1,R.id.tv2,R.id.tv3,R.id.tv4,R.id.tv5,R.id.tv6,R.id.tv7,R.id.tv8,R.id.tv9};
    int[] ratids={R.id.ratingBar1,R.id.ratingBar2,R.id.ratingBar3,R.id.ratingBar4,R.id.ratingBar5,R.id.ratingBar6,R.id.ratingBar7,R.id.ratingBar8,R.id.ratingBar9};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        setTitle("투표 결과");
        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("voteCount");
        String[] titles = intent.getStringArrayExtra("titles");
        int drawable = intent.getIntExtra("drawable",0);
        iv=findViewById(R.id.ivMax);
        iv.setImageResource(drawable);
        for(int i=0; i<tvIds.length; i++){
            tv[i]=findViewById(tvIds[i]);
            ratingBars[i]=findViewById(ratids[i]);
            tv[i].setText(titles[i]);
            ratingBars[i].setRating((float)voteResult[i]);
        }
        btn=findViewById(R.id.btnClose);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}