package com.example.dcornea.musicalstructureapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Genres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        /*
         * BOOKS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Books button
         * the activity_books.xml is displayed
         */
        Button books = (Button) findViewById(R.id.front_book);
        books.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent bookIntent = new Intent(Genres.this, Books.class);
                startActivity(bookIntent);
            }
        });

          /*
         * AUTHORS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Authors button
         * the activity_authors.xml is displayed
         */
        Button authors = (Button) findViewById(R.id.front_auth);
        authors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent authIntent = new Intent(Genres.this, Authors.class);
                startActivity(authIntent);
            }
        });
        //end for "Authors" button

    }
}
