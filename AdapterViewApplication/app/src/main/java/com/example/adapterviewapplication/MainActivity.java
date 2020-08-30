package com.example.adapterviewapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] name={"홍길동","한재현","김림용","이하윤","조양현"};
//    ArrayAdapter<String> adapter;
    int[] ids = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
            R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
    String[] title = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    GridView gridView;
    MyGridAdapter adapter;

//    Spinner spinner;
//    ImageView imageView;
//    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        listView=findViewById(R.id.listView1);
//        adapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_list_item_1, name
//        );
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, name[position], Toast.LENGTH_LONG).show();
//            }
//        });
        /*gridview*/
        setContentView(R.layout.grid_layout);
        gridView = findViewById(R.id.gridView);
        adapter = new MyGridAdapter(this);
        gridView.setAdapter(adapter);

//        setContentView(R.layout.spinner_layout);
//        spinner=findViewById(R.id.spinner);
//        imageView=findViewById(R.id.imageView);
//        adapter = new ArrayAdapter<>(
//                this, android.R.layout.simple_spinner_item, title
//        );
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
//                imageView.setImageResource(ids[position]);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        int[] ids = {R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05,
                R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10};
        String[] title = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        public MyGridAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return ids.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView iv = new ImageView(context);
            iv.setLayoutParams(new GridView.LayoutParams(200, 300)); // 이미지 사이즈
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER); //이미지를 어떤 방법으로 넣을건가 중앙정렬
            iv.setPadding(5, 5, 5, 5);
            iv.setImageResource(ids[position]);
            final int pos = position;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(MainActivity.this, title[pos], Toast.LENGTH_LONG).show();
                }
            });
            return iv;
        }
    }
}
