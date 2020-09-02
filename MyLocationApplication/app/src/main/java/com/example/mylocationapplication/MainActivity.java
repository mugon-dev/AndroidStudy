package com.example.mylocationapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    TextView tv;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });

        //매니페스트의 모든 permission 불러옴
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }
    //위치 얻어오는 메서드 선언
    private void startLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE); /*위치서비스 매니저 얻어오기*/
        /*위치정보 얻기*/
        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER); /*GPS provider 가지고 있는 가장 마지막 위치*/
            if(location!=null){
                double latitude = location.getLatitude(); /*위도 얻기*/
                double longitude = location.getLongitude(); /*경도 얻기*/
                String message = "최근 위치-> \nLatitude: "+latitude+"\nLongitude: "+longitude;
                tv.setText(message);
            }
            GPSListener listener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;
            //위치 업데이트 요청
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,listener);
            //업데이트 결과 toast
            Toast.makeText(getApplicationContext(),"내 위치확인 요청",Toast.LENGTH_LONG).show();
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }
    //위치 업데이트 onCreate 함수와 같은 레벨에서 class 선언
    class GPSListener implements LocationListener{
        //위치 받아오기
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String message="내 위치-> \nLatitude: "+latitude+"\nLongitude: "+longitude;
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(@NonNull String provider) {

        }

        @Override
        public void onProviderDisabled(@NonNull String provider) {

        }
    }


    //permission 결과
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this/*현재 액티비티*/,requestCode/*어떤 요청코드 수락했는지*/,permissions/*요청코드의 permission*/,this);
    }
    //거절된 permission
    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"permission denied: "+strings.length,Toast.LENGTH_LONG ).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permission granted: "+strings.length,Toast.LENGTH_LONG ).show();
    }
}