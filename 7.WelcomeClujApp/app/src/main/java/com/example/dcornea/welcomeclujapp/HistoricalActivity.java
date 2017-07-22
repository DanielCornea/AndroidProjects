package com.example.dcornea.welcomeclujapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dcornea on 6/26/17.
 */

public class HistoricalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);
        // Populate the array with Historical Monuments
        final ArrayList<Location> histSites = new ArrayList<Location>();
        histSites.add(new Location(getString(R.string.matia), getString(R.string.king), getString(R.string.visit), R.drawable.mcorvin));
        histSites.add(new Location(getString(R.string.mviteazu), getString(R.string.first), getString(R.string.visit), R.drawable.mviteazu));
        histSites.add(new Location(getString(R.string.bnovac), getString(R.string.cmv), getString(R.string.serb), R.drawable.bnovac));
        histSites.add(new Location(getString(R.string.acuza), getString(R.string.rtex), getString(R.string.gpro), R.drawable.acuza));
        histSites.add(new Location(getString(R.string.aiancu), getString(R.string.captain), getString(R.string.romani), R.drawable.aiancu));
        histSites.add(new Location(getString(R.string.sgeorge), getString(R.string.devil), getString(R.string.pray), R.drawable.sgheorghe));
        //setting the background collor
        LocationAdapter adapter = new LocationAdapter(this, histSites, R.color.colorPrimaryDark);
        //linking array with list
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
    }


}
