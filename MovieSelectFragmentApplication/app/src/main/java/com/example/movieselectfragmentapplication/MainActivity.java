package com.example.movieselectfragmentapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnMovieSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.movieList_container)!=null){
            if(savedInstanceState!=null){
                return;
            }
            MovieListFragment fragment = new MovieListFragment();
            fragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.movieList_container,fragment).commit();
        }
    }

    @Override
    public void onMovieSelected(int position) {
        MovieImageFragment imageFragment = (MovieImageFragment) getSupportFragmentManager().findFragmentById(R.id.movieImage_container);
        if(imageFragment != null){
            imageFragment.updateDefinitionView(position);
        }else {
            MovieImageFragment newFragment = new MovieImageFragment();
            Bundle args = new Bundle();
            args.putInt(MovieImageFragment.ARG_POSITION,position);
            newFragment.setArguments(args);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.movieImage_container,newFragment);
            ft.commit();
        }
    }
}