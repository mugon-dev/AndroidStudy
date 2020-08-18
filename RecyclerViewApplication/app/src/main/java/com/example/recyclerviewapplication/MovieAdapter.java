package com.example.recyclerviewapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements OnMovieItemClickListener {
    ArrayList<Movie> movieItems = new ArrayList<Movie>();
    OnMovieItemClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);

        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item = movieItems.get(position);
        holder.setItem(item); // viewHolder 안에 아이템 넣기

    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public void addItem(Movie item) {
        movieItems.add(item);
    }

    public void setMovieItems(ArrayList<Movie> items) {
        movieItems = items;
    }

    public Movie getItem(int position) {
        return movieItems.get(position);
    }

    public void setItem(int postion, Movie item) {
        movieItems.set(postion, item); //특정위치에 아이템 삽입
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    public void setOnItemClickListener(OnMovieItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvTitle, tvGenre, tvDirector;

        public ViewHolder(@NonNull View itemView, final OnMovieItemClickListener listener) {
            super(itemView);
            iv = itemView.findViewById(R.id.imageView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvGenre = itemView.findViewById(R.id.tvGenre);
            tvDirector = itemView.findViewById(R.id.tvDirector);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(ViewHolder.this, view, position);
                    }
                }
            });
        }

        public void setItem(Movie movie) {
            iv.setImageResource(movie.getResId());
            tvTitle.setText(movie.getTitle());
            tvGenre.setText(movie.getGenre());
            tvDirector.setText(movie.getDirector());
        }
    }
}
