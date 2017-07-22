package com.example.android.guradiannewsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//crating a new adapter
//and linking it with the Article class

public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.article_item, parent, false);
        }
        final Article currentArticle = getItem(position);
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String title = currentArticle.getTitle();
        titleView.setText(title);
        final TextView authorView = (TextView) listItemView.findViewById(R.id.section);
        String section = currentArticle.getSection();
        authorView.setText(section);


        titleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(currentArticle.getUrl()));
                getContext().startActivity(i);


            }
        });
        return listItemView;
    }
}