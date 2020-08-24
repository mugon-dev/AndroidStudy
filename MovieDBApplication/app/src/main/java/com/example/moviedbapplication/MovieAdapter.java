package com.example.moviedbapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHoler> {
    ArrayList<Movie> items = new ArrayList<>();

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHoler(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, int position) {
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

    //제일 먼저 생성
    static class ViewHoler extends RecyclerView.ViewHolder {
        TextView tvItemTitle;
        TextView tvItemPoint;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
            tvItemPoint = itemView.findViewById(R.id.tvItemPoint);
        }

        public void setItem(Movie movie) {
            double avg = movie.getPoint() / movie.getCount();
            tvItemTitle.setText(movie.getTitle());
            tvItemPoint.setText("평점: "+avg + " 점");

        }
    }
}
