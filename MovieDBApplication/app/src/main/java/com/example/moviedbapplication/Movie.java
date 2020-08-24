package com.example.moviedbapplication;

public class Movie {
    private int id;
    private String title;
    private String genre;
    private String director;
    private int year;
    private String actor;
    private String content;
    private double point;
    private int count;

    public Movie() {
    }

    public Movie(String title, String genre, String director, int year, String actor, String content) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.actor = actor;
        this.content = content;
    }

    public Movie(String title, String genre, String director, int year, String actor, String content, double point, int count) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.actor = actor;
        this.content = content;
        this.point = point;
        this.count = count;
    }

    public Movie(int id, String title, String genre, String director, int year, String actor, String content, double point, int count) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.actor = actor;
        this.content = content;
        this.point = point;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
