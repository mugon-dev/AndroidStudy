package com.example.framelayoutaplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    RadioGroup[] rb = new RadioGroup[3];
    int[] rbIds={R.id.rbDog, R.id.rbCat, R.id.rbRabbit};
    ImageView[] iv = new ImageView[3];
    int[] ivIds={R.id.ivDog, R.id.ivCat, R.id.ivRabbit};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i=0; i<rb.length; i++){
            final int idx=i;
            rb[i]=findViewById(rbIds[i]);
            iv[i]=findViewById(ivIds[i]);
            rb[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for(int j=0; j<iv.length; j++){
                        if(j==idx){
                            iv[j].setVisibility(View.VISIBLE);
                        }else{
                            iv[j].setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }
    }
}