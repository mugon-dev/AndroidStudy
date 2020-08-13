package com.example.votetestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView[] iv = new ImageView[9];
    int[] ivIds={R.id.imageView,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9};
    String[] titles={"독서하는 소녀","꽃장식 모자 소녀","부채를 든 소녀","이레느깡 단 베르양","잠자는 소녀","테라스의 두자매","피아노 레슨","피아노 앞의 소녀","해변에서"};
    int[] voteCount = new int[9];
    int[] drawbles={R.drawable.renoir01,R.drawable.renoir02,R.drawable.renoir03,R.drawable.renoir04,R.drawable.renoir05,R.drawable.renoir06,R.drawable.renoir07,R.drawable.renoir08,R.drawable.renoir09};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("명화 선호도 투표");
        btn=findViewById(R.id.btnFinish);
        for(int i=0; i<iv.length; i++){
            final int idx=i;
            iv[i]=findViewById(ivIds[i]);
            iv[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    voteCount[idx]++;
                    Toast.makeText(getApplicationContext(),titles[idx]+" : 총 "+voteCount[idx]+" 표",Toast.LENGTH_SHORT).show();
                }
            });
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                int max=0;
                int idx=-1;
                for(int i=0; i<voteCount.length; i++){
                    if(voteCount[i]>max){
                        idx=i;
                        max=voteCount[i];
                    }
                }
                intent.putExtra("voteCount",voteCount);
                intent.putExtra("titles",titles);
                intent.putExtra("drawable",drawbles[idx]);
                startActivity(intent);
            }
        });
    }
}