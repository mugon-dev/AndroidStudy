package com.example.albumapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    ImageView iv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.button);
        iv=findViewById(R.id.imageView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,101);
    }

    private void openGallery(){
        //클래스를 모를때
        Intent intent = new Intent();
        //이미지 폴더의 모든 것
        intent.setType("image/*");
        //실제 파일 가져오기
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //값을 돌려받을 예정->onActivityResult 재정의
        startActivityForResult(intent,101);
    }

    @Override
    protected void onActivityResult(int requestCode/*어떤 activity를 호출하였는 구별해주는 역할*/, int resultCode/*엑티비티 종료시 호출되어야되는 결과가 2가지 이상일때*/,
                                    @Nullable Intent data/*보낼때 데이터*/) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            if(resultCode==RESULT_OK){
                Uri fileUri = data.getData();
                ContentResolver resolver = getContentResolver();
                try{
                    InputStream is = resolver.openInputStream(fileUri);
                    //이미지라서 bitmap으로 가져옴
                    //sd카드에 들어있는 파일은 디코딩이 필요 반면 drawable은 이미 디코딩 되어있음
                    Bitmap bitmap = BitmapFactory.decodeStream(is);
                    iv.setImageBitmap(bitmap);
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults/*권한 허용 결과*/) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    @Override
    public void onDenied(int i, String[] strings/*permission 이름*/) {
        Toast.makeText(this,"permission denied: "+strings.length,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permission granted: "+strings.length,Toast.LENGTH_LONG).show();
    }
}