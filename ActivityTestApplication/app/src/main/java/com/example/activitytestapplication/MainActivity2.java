package com.example.activitytestapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {
    Button btnSeconde, btnMyActivity;
    EditText edName, edAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btnSeconde=findViewById(R.id.btnSecond);
        btnMyActivity=findViewById(R.id.btnMyActivity);
        edName=findViewById(R.id.editName);
        edAge=findViewById(R.id.editAge);
        btnSeconde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 클릭시 불러울 액티비티 클래스를 현재 액티비티의 intent객체에 저장
                Intent intent = new Intent(getApplicationContext(),SecondActivity.class);
                //intent에 값,데이터 쌍으로 저장
                intent.putExtra("name",edName.getText().toString());
                intent.putExtra("age",edAge.getText().toString());
                //intent에 저장한 액티비티 실행
                startActivity(intent);
            }
        });
    }
}