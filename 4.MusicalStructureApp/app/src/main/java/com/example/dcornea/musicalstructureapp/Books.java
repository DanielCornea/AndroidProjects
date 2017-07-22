package com.example.dcornea.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Books extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);


        /*
         *
         * PLAY BUTTON FOR THE FIRST BOOK
         * The code below listens to a users action
         * and when the user "click" on the icon
         * the activity_player.xml is displayed
         *
         */

        Button player = (Button) findViewById(R.id.button_0);
        player.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playIntent = new Intent(Books.this, Player.class);
                startActivity(playIntent);
            }
        });


        /*
         *
         * PLAY BUTTON FOR THE SECOND BOOK
         * The code below listens to a users action
         * and when the user "click" on the icon
         * the activity_player.xml is displayed
         *
         */

        Button player1 = (Button) findViewById(R.id.button_1);
        player1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent playIntent1 = new Intent(Books.this, Player.class);
                startActivity(playIntent1);
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
                Intent authIntent = new Intent(Books.this, Authors.class);
                startActivity(authIntent);
            }
        });
        //end of "Authors" button

        /*
         * GENRES
         * The code below "listens" to a user's action
         * and when the user "clicks" on the Genres button
         * the activity_genres.xml is displayed
         */
        Button genres = (Button) findViewById(R.id.front_genre);
        genres.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent genreIntent = new Intent(Books.this, Genres.class);
                startActivity(genreIntent);
            }
        });
        //end of "Genres" Buttons
    }
}
