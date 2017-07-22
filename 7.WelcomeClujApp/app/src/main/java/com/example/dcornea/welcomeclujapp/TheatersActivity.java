package com.example.dcornea.welcomeclujapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dcornea on 6/26/17.
 */

public class TheatersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        final ArrayList<Location> theaters = new ArrayList<Location>();
        theaters.add(new Location(getString(R.string.nati), getString(R.string.romath), getString(R.string.audi)));
        theaters.add(new Location(getString(R.string.hungar), getString(R.string.stateHungar), getString(R.string.inglese)));
        theaters.add(new Location(getString(R.string.persic), getString(R.string.gractor), getString(R.string.nouveau)));
        theaters.add(new Location(getString(R.string.vict), getString(R.string.commie), getString(R.string.capi)));
        theaters.add(new Location(getString(R.string.city), getString(R.string.marv), getString(R.string.exp1)));
        theaters.add(new Location(getString(R.string.mara), getString(R.string.mth1), getString(R.string.nei)));
        theaters.add(new Location(getString(R.string.mana), getString(R.string.air), getString(R.string.mth1)));


        LocationAdapter adapter = new LocationAdapter(this, theaters, R.color.colorAccent);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }
}
