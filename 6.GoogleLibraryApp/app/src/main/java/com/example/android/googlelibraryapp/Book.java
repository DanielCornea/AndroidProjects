package com.example.android.googlelibraryapp;

/**
 * Created by danok on 7/14/2017.
 */
//this one is pretty straight forward
//it contains the properties and the get methods of Book object

public class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
}
