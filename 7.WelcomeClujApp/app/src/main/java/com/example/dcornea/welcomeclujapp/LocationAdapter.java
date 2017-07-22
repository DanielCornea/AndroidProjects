package com.example.dcornea.welcomeclujapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class LocationAdapter extends ArrayAdapter<Location> {

    private int colorLocationId;

    // Providing the constructor for Class
    public LocationAdapter(Context context, ArrayList<Location> location, int colorResourceId) {
        super(context, 0, location);
        colorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // get the {@link location} object located at this position in the list
        Location currentLocation = getItem(position);

        // find the name textView in the list_item.xml
        TextView nameTextView = (TextView) listItemView.findViewById(R.id.list_name);

        // get the name from location object and set it to the textView
        nameTextView.setText(currentLocation.getTitle());


        // find the first attribute textView in the list_item.xml
        TextView atribute1TextView = (TextView) listItemView.findViewById(R.id.attr1_value);

        // get the first attribute from location object and set it to the textView
        atribute1TextView.setText(currentLocation.getAtribute1());

        // find the second attribute textView in the list_item.xml
        TextView attribute2TextView = (TextView) listItemView.findViewById(R.id.attr2_value);

        // get the second attribute from location object and set it to the textView
        attribute2TextView.setText(currentLocation.getAtribute2());

        ImageView imageLeft = (ImageView) listItemView.findViewById(R.id.list_image_left);

        // Check weather there is an image or not
        if (currentLocation.hasImage1()) {
            imageLeft.setImageResource(currentLocation.getImage_resource1());
            imageLeft.setVisibility(View.VISIBLE);
        } else {
            imageLeft.setVisibility(View.GONE);
        }
        return listItemView;
    }


}
