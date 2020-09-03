package com.example.googlemapapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener /*implements OnMapReadyCallback*//*맵 이벤트 처리 인터페이스*/ {

    private GoogleMap mMap;
    Button btn;
    SupportMapFragment mapFragment;
    MarkerOptions myLocationMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        btn=findViewById(R.id.button);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        /*mapFragment.getMapAsync(this);*/
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;
            }
        });
        try{
            MapsInitializer.initialize(this);
        }catch (Exception e){
            e.printStackTrace();
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLocationService();
            }
        });
        AutoPermissions.Companion.loadAllPermissions(this,101);
    }
    private void startLocationService(){
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        try{
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(location!=null){
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> latitude: "+latitude+" longitude: "+longitude;
                Log.i("Map",message);
            }
            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance,gpsListener);
            Toast.makeText(getApplicationContext(),"내 위치확인 요청함",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    class GPSListener implements LocationListener{
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();
            String message = "내 위치 -> latitude: "+latitude+" longitude: "+longitude;
            Log.i("Map",message);
            showCurrentLocation(latitude,longitude);
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
    private void showCurrentLocation(Double latitude,Double longitude){
        LatLng curPoint = new LatLng(latitude,longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint,15));
        showMarker(curPoint);
    }
    private void showMarker(LatLng curPoint){
        if(myLocationMarker == null){
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("내위치\n");
            myLocationMarker.snippet("GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            mMap.addMarker(myLocationMarker);
        }else {
            myLocationMarker.position(curPoint);
            mMap.addMarker(myLocationMarker);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this,requestCode,permissions,this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151); *//*지도 킬때 나타나는 위치 지정*//*
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney")); *//*지정한 위치에 마크*//*
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*//*내가 보고 있는 위치를 내가 지정한 위치로 카메라 이동*//*
    }*/

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this,"permission denied"+strings.length,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this,"permission granted"+strings.length,Toast.LENGTH_LONG).show();
    }
}