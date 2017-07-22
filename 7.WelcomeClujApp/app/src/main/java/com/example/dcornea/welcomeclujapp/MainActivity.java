package com.example.dcornea.welcomeclujapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inflate the universities list
        TextView universities = (TextView) findViewById(R.id.main_univ);
        universities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent universitiesIntent = new Intent(MainActivity.this, UniversitiesActivity.class);
                startActivity(universitiesIntent);
            }
        });
        // Inflate the restaurants list
        TextView restaurants = (TextView) findViewById(R.id.main_res);
        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent restaurantsIntent = new Intent(MainActivity.this, RestaurantsActivity.class);
                startActivity(restaurantsIntent);
            }
        });
        // Inflate the Historical Sites list
        TextView histSites = (TextView) findViewById(R.id.main_sites);
        histSites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent histSitesIntent = new Intent(MainActivity.this, HistoricalActivity.class);
                startActivity(histSitesIntent);
            }
        });
        // Inflate the Theaters list
        TextView theaters = (TextView) findViewById(R.id.main_theatre);
        theaters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent theatersIntent = new Intent(MainActivity.this, TheatersActivity.class);
                startActivity(theatersIntent);
            }
        });

    }
}
