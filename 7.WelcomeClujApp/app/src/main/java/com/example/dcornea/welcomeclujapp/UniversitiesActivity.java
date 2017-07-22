package com.example.dcornea.welcomeclujapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by dcornea on 6/26/17.
 */

public class UniversitiesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_list);

        final ArrayList<Location> universities = new ArrayList<Location>();
        universities.add(new Location(getString(R.string.fsega), getString(R.string.bili), getString(R.string.bili)));
        universities.add(new Location(getString(R.string.fspac), getString(R.string.anch), getString(R.string.avoid)));
        universities.add(new Location(getString(R.string.sstr), getString(R.string.tribe), getString(R.string.layer)));
        universities.add(new Location(getString(R.string.theo), getString(R.string.zros), getString(R.string.pray)));
        universities.add(new Location(getString(R.string.rob), getString(R.string.cybi), getString(R.string.gul)));
        universities.add(new Location(getString(R.string.compi), getString(R.string.letter), getString(R.string.yeah)));
        universities.add(new Location(getString(R.string.poli), getString(R.string.even1), getString(R.string.asdf)));


        LocationAdapter adapter = new LocationAdapter(this, universities, R.color.colorPrimaryDark);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
    }


}
