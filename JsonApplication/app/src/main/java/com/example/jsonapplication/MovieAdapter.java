package com.example.jsonapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements OnMovieItemClickListener {
    ArrayList<Movie> items = new ArrayList<>();
    OnMovieItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //만든 레이아웃을 인플레터 시켜줘야함
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, parent/*붙일 곳*/, false/*바로 붙일 것인가*/);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //리사이클 뷰에 몇번째 데이터가 들어갈것인가
        Movie item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item) {
        items.add(item);
    }

    public void setItems(ArrayList<Movie> items) {
        this.items = items;
    }

    public Movie getItem(int position) {
        return items.get(position);
    }

    //클릭이벤트는 인터페이스 만들어서 임플리먼트해야함
    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public void setOnItemClickListener(OnMovieItemClickListener listener) {
        this.listener = listener;
    }

    //recyclerView 할땐 viewholder 먼저 선언
    static class ViewHolder extends RecyclerView.ViewHolder {
        //텍스트뷰의 내용만 바뀜
        TextView tvTitle, tvCnt;

        public ViewHolder(@NonNull View itemView, final OnMovieItemClickListener listener) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCnt = itemView.findViewById(R.id.tvCnt);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this,view,position);
                    }
                }
            });
        }

        public void setItem(Movie item) {
            tvTitle.setText(item.movieNm);
            tvCnt.setText(item.audiCnt + " 명");
        }
    }
}
