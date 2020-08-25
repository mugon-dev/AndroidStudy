package com.example.moviedbapplication;

import android.view.View;

public interface OnMovieItemClickListener {
    public void OnItemClick(MovieAdapter.ViewHolder holer, View view, int position);
}
