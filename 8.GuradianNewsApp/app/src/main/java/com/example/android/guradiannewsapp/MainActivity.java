package com.example.android.guradiannewsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Article>> {

    //    here starts the fun
//    api url
    private static final String ARTICLE_REQUEST_URL =
            "https://content.guardianapis.com/search?api-key=06a018d8-57f5-43ac-99a7-f716bd150c70";
    private static final int ARTICLE_LOADER_ID = 1;

//    variable declaration

    //    textview for empty activity
    private TextView showNothing;

    //    var for adapter
    private ArticleAdapter articleAdapter;

    //    vars related to the load manager
    public LoaderManager loaderManager = getLoaderManager();


    //entry point in the app
//    this method inflates the main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        binding back-end variables to the views
        ListView articleListview = (ListView) findViewById(R.id.list);
        showNothing = (TextView) findViewById(R.id.show_nothing);
        articleListview.setEmptyView(showNothing);

//        linking the adapter with the listView
        articleAdapter = new ArticleAdapter(this, new ArrayList<Article>());
        articleListview.setAdapter(articleAdapter);
//        making sure we have acces to netwrk and loading the manager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            loaderManager = getLoaderManager();

            // Initializing the loader.
            loaderManager.initLoader(ARTICLE_LOADER_ID, null, this);

        } else
            showNothing.setText(R.string.no_internet_connection);
    }

    //    starting the connection with the api server
    @Override
    public Loader<List<Article>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(ARTICLE_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("section", (String) getResources().getText(R.string.business));


        return new ArticleLoader(this, uriBuilder.toString());
    }

    //    required method by the load manager
    @Override
    public void onLoadFinished(Loader<List<Article>> loader, List<Article> articles) {
        // Set empty state text to display "No articles available."
        showNothing.setText(R.string.no_articles);
        // Clear the adapter of previous articles data
        articleAdapter.clear();
        // If there is a valid list of articles, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (articles != null && !articles.isEmpty()) {
            articleAdapter.addAll(articles);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Article>> loader) {
        // Loader reset, so we can clear out our existing data.
        articleAdapter.clear();
    }
}
