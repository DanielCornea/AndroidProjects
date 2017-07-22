package com.example.dcornea.welcomeclujapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dcornea on 6/26/17.
 */

public class RestaurantsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        final ArrayList<Location> restaurants = new ArrayList<Location>();
        restaurants.add(new Location(getString(R.string.monkey), getString(R.string.prices), getString(R.string.date)));
        restaurants.add(new Location(getString(R.string.amici), getString(R.string.lux), getString(R.string.ritchie)));
        restaurants.add(new Location(getString(R.string.klays), getString(R.string.saus), getString(R.string.germ)));
        restaurants.add(new Location(getString(R.string.klays2), getString(R.string.klass), getString(R.string.next)));
        restaurants.add(new Location(getString(R.string.tol), getString(R.string.mobs), getString(R.string.exp)));
        restaurants.add(new Location(getString(R.string.sams), getString(R.string.food), getString(R.string.curry)));
        restaurants.add(new Location(getString(R.string.must), getString(R.string.attache), getString(R.string.cheap)));
        restaurants.add(new Location(getString(R.string.janis), getString(R.string.kids), getString(R.string.friend)));
        restaurants.add(new Location(getString(R.string.beams), getString(R.string.stud), getString(R.string.fun)));


        LocationAdapter adapter = new LocationAdapter(this, restaurants, R.color.colorAccent);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
