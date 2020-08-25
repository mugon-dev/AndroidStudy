package com.example.moviedbapplication;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends
        RecyclerView.Adapter<MovieAdapter.ViewHolder>
        implements OnMovieItemClickListener{

    ArrayList<Movie> items=new ArrayList<Movie>(); //수정
    OnMovieItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.movie_item,parent,false);
        return new ViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item=items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Movie item){
        items.add(item);
    }
    public void setItems(ArrayList<Movie> items){
        this.items=items;
    }
    public Movie getItem(int position){
        return items.get(position);
    }



    public void setOnItemClickListener(OnMovieItemClickListener listener){
        this.listener=listener;
    }

    @Override
    public void OnItemClick(MovieAdapter.ViewHolder holder, View view, int position) {
        if(listener!=null){
            listener.OnItemClick(holder,view,position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvItemTitle;
        TextView tvItemPoint;

        public ViewHolder(@NonNull View itemView, final OnMovieItemClickListener listener) {
            super(itemView);
            tvItemTitle=itemView.findViewById(R.id.tvItemTitle);
            tvItemPoint=itemView.findViewById(R.id.tvItemPoint);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();

                    if(listener !=null){
                        listener.OnItemClick(ViewHolder.this,view,position);
                    }
                }
            });
        }

        public void setItem(Movie movie){
            double avg=0;
            if(movie.getCount()>0){
                avg=movie.getPoint()/movie.getCount();
            }
            tvItemTitle.setText(movie.getTitle());
            tvItemPoint.setText("평점:"+avg+"점");
        }
    }
}
