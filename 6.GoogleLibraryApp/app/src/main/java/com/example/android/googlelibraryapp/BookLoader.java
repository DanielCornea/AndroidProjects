package com.example.android.googlelibraryapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


//here we handle the asyncTasks
public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private static final String LOG_TAG = BookLoader.class.getName();
    private String getBookUrl;

    public BookLoader(Context context, String url) {
        super(context);
        getBookUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Book> loadInBackground() {
        if (getBookUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of books.
        List<Book> books = QuerryUtilities.getBookData(getBookUrl);
        return books;
    }
}
