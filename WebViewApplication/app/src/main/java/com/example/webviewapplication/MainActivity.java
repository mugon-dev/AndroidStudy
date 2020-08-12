package com.example.webviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    Button btnGo, btnBack;
    EditText ed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv=findViewById(R.id.webView1);
        btnGo=findViewById(R.id.btnGo);
        btnBack=findViewById(R.id.btnBack);
        ed=findViewById(R.id.edUrl);
        //1. webview client 설정
        wv.setWebViewClient(new WebViewClient1());
        //2. webview 세팅값 가져오기
        WebSettings webSettings = wv.getSettings();
        //3. webview 줌인 추가
        webSettings.setBuiltInZoomControls(true);
        //4. manifest에서 외부 인터넷 사용권한 추가
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.loadUrl(ed.getText().toString());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wv.goBack();
            }
        });
    }
    class WebViewClient1 extends android.webkit.WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }
    }
}