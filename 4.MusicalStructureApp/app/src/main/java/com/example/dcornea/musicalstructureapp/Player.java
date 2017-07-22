package com.example.dcornea.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Player extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        /*
         * BOOKS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Books button
         * the activity_books.xml is displayed
         */
        TextView books = (TextView) findViewById(R.id.button_player_audiobooks);
        books.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent bookIntent = new Intent(Player.this, Books.class);
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
        TextView genres = (TextView) findViewById(R.id.button_player_genres);
        genres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent genreIntent = new Intent(Player.this, Genres.class);
                startActivity(genreIntent);
            }
        });
        //end of "Genres" button

         /*
         * AUTHORS
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Authors button
         * the activity_authors.xml is displayed
         */
        Button authors = (Button) findViewById(R.id.button_player_authors);
        authors.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent authIntent = new Intent(Player.this, Authors.class);
                startActivity(authIntent);
            }
        });
        //end of "Authors" button

         /*
         * MainActivity
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Books button
         * the activity_main.xml is displayed
         */
        Button home = (Button) findViewById(R.id.button_player_home);
        home.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent homeIntent = new Intent(Player.this, MainActivity.class);
                startActivity(homeIntent);
            }
        });
        //end for "Books" button
    }
}
