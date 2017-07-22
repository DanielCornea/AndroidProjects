package com.example.dcornea.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Authors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authors);

        /*
         * BOOKS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Books button
         * the activity_books.xml is displayed
         */
        Button books = (Button) findViewById(R.id.front_book);
        books.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent bookIntent = new Intent(Authors.this, Books.class);
                startActivity(bookIntent);
            }
        });
        //end for "Books" button

        /*
         * GENRES
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Genres button
         * the activity_genres.xml is displayed
         */
        TextView genres = (TextView) findViewById(R.id.front_genre);
        genres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent genreIntent = new Intent(Authors.this, Genres.class);
                startActivity(genreIntent);
            }
        });
        //end of "Genres" button
    }
}
