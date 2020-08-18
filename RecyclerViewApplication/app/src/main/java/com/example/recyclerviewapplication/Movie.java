package com.example.recyclerviewapplication;

public class Movie {
    private String title;
    private String genre;
    private String director;
    private int resId;

    public Movie() {}

    public Movie(String title, String genre, String director, int resId) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
