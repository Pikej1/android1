package com.example.a195654.lab4;

import java.io.Serializable;

/**
 * Created by PiET on 2017-05-18.
 */

public class Movie implements Serializable{
    private String title;
    private String director;
    private String genre;
    private String language;

    public Movie(){}

    public Movie(String title, String director){//, String genre, String language){
        this.title = title;
        this.director = director;
        //this.genre = genre;
        //this.language = language;
    }

    public String toString(){
        return getTitle();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTitle() {

        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }
}
