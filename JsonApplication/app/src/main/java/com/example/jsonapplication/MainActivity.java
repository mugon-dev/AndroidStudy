package com.example.jsonapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    Button btn;
    RecyclerView recyclerView;
    MovieAdapter adapter;
    LinearLayoutManager layoutManager;

    static RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed=findViewById(R.id.edit);
        btn=findViewById(R.id.button);

        recyclerView = findViewById(R.id.recycleView);
        adapter = new MovieAdapter();
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OnMovieItemClickListener() {
            @Override
            public void onItemClick(MovieAdapter.ViewHolder holder, View view, int position) {
                Toast.makeText(getApplicationContext(),
                        adapter.items.get(position).movieNm,
                        Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(getApplicationContext());
        }
    }

    private void makeRequest() {
        String url = ed.getText().toString();
        StringRequest request = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        processResponse(response);
                        //printTv("응답-> "+response);
                        printLog("응답-> "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //printTv("에러-> "+error.getMessage());
                        printLog("에러-> "+error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                return params;
            }
        };
        request.setShouldCache(false);
        requestQueue.add(request);
//        printTv("요청보냄");
        printLog("요청보냄");
    }
    private void processResponse(String response){
        Gson gson = new Gson();
        MovieList movieList = gson.fromJson(response,MovieList.class); //response의 결과값을 movielist.class 형태로 저장
//        printTv("영화 정보의 수 : "+movieList.boxOfficeResult.dailyBoxOfficeList.size());
        printLog("영화 정보의 수 : "+movieList.boxOfficeResult.dailyBoxOfficeList.size());
        for(int i=0; i<movieList.boxOfficeResult.dailyBoxOfficeList.size(); i++ ){
            Movie movie = movieList.boxOfficeResult.dailyBoxOfficeList.get(i); //영화정보 하나
            adapter.addItem(movie);
        }
        adapter.notifyDataSetChanged();
    }
    /*private void printTv(String data){
        tv.append(data+"\n");
    }*/
    private void printLog(String data){
        Log.d("Movies_Info",data);
    }
}