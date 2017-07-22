package com.example.android.googlelibraryapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//above you can find the imports
public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<Book>> {

    //    here starts the fun
//    api url
    private static final String BOOK_REQUEST_URL =
            "https://www.googleapis.com/books/v1/volumes";
    private static final int BOOK_LOADER_ID = 1;

//    variable declaration

    //    textview for empty activity
    private TextView showNothing;

    //    variable to store the user input
    private String searchBookQuery = null;

    //    var for adapter
    private BookAdapter bookAdapter;

    //    vars related to the load manager
    private LoaderManager.LoaderCallbacks callbacks = this;
    public LoaderManager loaderManager = getLoaderManager();


    //entry point in the app
//    this method inflates the main activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        binding back-end variables to the views
        ListView bookListview = (ListView) findViewById(R.id.list);
        showNothing = (TextView) findViewById(R.id.show_nothing);
        bookListview.setEmptyView(showNothing);

//        linking the adapter with the listView
        bookAdapter = new BookAdapter(this, new ArrayList<Book>());
        bookListview.setAdapter(bookAdapter);

//search button and the text view
        Button searchButton = (Button) findViewById(R.id.button_search);
        final EditText searchQuery = (EditText) findViewById(R.id.input_text);
//        making sure we have acces to netwrk and loading the manager


        searchButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
//                                                the click makes manager loader to restart with the user input querry
                                                searchBookQuery = searchQuery.getText().toString();
                                                Log.v("SEARCH BOOK QUERRY", searchBookQuery);
                                                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                                                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                                                if (networkInfo != null && networkInfo.isConnected())
                                                    getLoaderManager().restartLoader(BOOK_LOADER_ID, null, callbacks);
                                                else{
                                                    showNothing.setText(R.string.no_internet_connection);
                                                    bookAdapter.clear();
                                                }
                                                                                                }
                                        }
        );

//        making sure we have acces to netwrk and loading the manager
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected() && (searchBookQuery != null)) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            loaderManager = getLoaderManager();

            // Initializing the loader.
            loaderManager.initLoader(BOOK_LOADER_ID, null, this);
            //first screen
        } else if (!(searchBookQuery != null)) {
            showNothing.setText(R.string.do_type);
            // Update empty state with no connection error message
        } else
            showNothing.setText(R.string.no_internet_connection);
    }

    //    starting the connection with the api server
    @Override
    public Loader<List<Book>> onCreateLoader(int i, Bundle bundle) {
        Uri baseUri = Uri.parse(BOOK_REQUEST_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("q", searchBookQuery);
        uriBuilder.appendQueryParameter("maxResults", "10");
        return new BookLoader(this, uriBuilder.toString());
    }

    //    required method by the load manager
    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        // Set empty state text to display "No books available."
        showNothing.setText(R.string.no_books);
        // Clear the adapter of previous books data
        bookAdapter.clear();
        // If there is a valid list of books, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (books != null && !books.isEmpty()) {
            bookAdapter.addAll(books);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        // Loader reset, so we can clear out our existing data.
        bookAdapter.clear();
    }
}