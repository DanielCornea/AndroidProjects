package com.example.dcornea.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
         * BOOKS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Books button
         * the activity_books.xml is displayed
         */
        Button books = (Button) findViewById(R.id.front_book);
        books.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent bookIntent = new Intent(MainActivity.this, Books.class);
                startActivity(bookIntent);
            }
        });
        //end for "Books" button

        /*
         * AUTHORS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Authors button
         * the activity_authors.xml is displayed
         */
        Button authors = (Button) findViewById(R.id.front_auth);
        authors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent authIntent = new Intent(MainActivity.this, Authors.class);
                startActivity(authIntent);
            }
        });
        //end for "Authors" button

        /*
         * GENRES
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Genres button
         * the activity_genres.xml is displayed
         */
        Button genres = (Button) findViewById(R.id.front_genre);
        genres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent genreIntent = new Intent(MainActivity.this, Genres.class);
                startActivity(genreIntent);
            }
        });
        //end of "Genres" button
    }
}
