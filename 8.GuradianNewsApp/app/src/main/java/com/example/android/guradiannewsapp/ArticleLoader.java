package com.example.android.guradiannewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;


//here we handle the asyncTasks
public class ArticleLoader extends AsyncTaskLoader<List<Article>> {
    private static final String LOG_TAG = ArticleLoader.class.getName();
    private String getArticleUrl;

    public ArticleLoader(Context context, String url) {
        super(context);
        getArticleUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread.
     */
    @Override
    public List<Article> loadInBackground() {
        if (getArticleUrl == null) {
            return null;
        }
        // Perform the network request, parse the response, and extract a list of articles.
        return QuerryUtilities.getArticleData(getArticleUrl);

    }
}
