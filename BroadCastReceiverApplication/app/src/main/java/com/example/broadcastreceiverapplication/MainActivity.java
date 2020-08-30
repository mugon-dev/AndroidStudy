package com.example.broadcastreceiverapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AutoPermissions.Companion.loadAllPermissions(this,101); //모든 위험권한
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this/*메인액티비티*/,requestCode,permissions,this /*이것을 구현하는 리스너는 메인액티비티이기떄문에 this*/);
    }
    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"permission denied: "+strings.length,Toast.LENGTH_LONG).show();
    }
    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permission granted: "+strings.length,Toast.LENGTH_LONG).show();
    }
}