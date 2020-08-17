package com.example.gridviewapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    GridView gv;
    MyGridAdapter myGridAdapter = null;
    View dialogView;
    Detail detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv = findViewById(R.id.gridView1);
        myGridAdapter = new MyGridAdapter(this);
        gv.setAdapter(myGridAdapter);
    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;
        int[] posterId = {
                R.drawable.mov01, R.drawable.mov02, R.drawable.mov03, R.drawable.mov04, R.drawable.mov05, R.drawable.mov06, R.drawable.mov07, R.drawable.mov08, R.drawable.mov09, R.drawable.mov10,
                R.drawable.mov11, R.drawable.mov12, R.drawable.mov13, R.drawable.mov14, R.drawable.mov15, R.drawable.mov16, R.drawable.mov17, R.drawable.mov18, R.drawable.mov19, R.drawable.mov20,
        };

        public MyGridAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return posterId.length;
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
            iv.setLayoutParams(new GridView.LayoutParams(200, 300)); //그리드뷰 전체 크기
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER); //이미지를 그리드뷰의 한 셀안에서 가운데 맞춤
            iv.setPadding(5, 5, 5, 5);
            iv.setImageResource(posterId[position]);
            final int pos = position;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialogView = View.inflate(
                            MainActivity.this,
                            R.layout.dialog1,
                            null);
                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivPoster = dialogView.findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(posterId[pos]);
                    dlg.setTitle("큰포스트");
                    dlg.setIcon(R.mipmap.ic_launcher);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기",null);
                    dlg.setPositiveButton("상세보기", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                            intent.putExtra("detail",detail);
                            startActivity(intent);
                        }
                    });
                    dlg.show();
                }
            });
            return iv;
        }
    }
}