package com.example.a195654.lab4;

import java.io.Serializable;

/**
 * Created by PiET on 2017-05-23.
 */

public class Book implements Serializable{
    private String title;
    private String author;
    private String genre;
    private boolean ifPolish;

    public Book(){}

    public Book(String title, String author){
        this.title = title;
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setIfPolish(boolean ifPolish) {
        this.ifPolish = ifPolish;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isIfPolish() {
        return ifPolish;
    }
}
