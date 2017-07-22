package com.example.android.guradiannewsapp;

/**
 * Created by danok on 7/14/2017.
 */
//this one is pretty straight forward
//it contains the properties and the get methods of Book object

public class Article {

    private String title;
    private String section;
    private String url;

    public Article(String title, String section, String url) {
        this.title = title;
        this.section = section;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getSection() {
        return section;
    }

    public String getUrl() {
        return url;
    }
}
