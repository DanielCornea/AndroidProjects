package com.example.android.googlelibraryapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

//crating a new adapter
//and linking it with the Book class

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> books) {
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }
        Book currentBook = getItem(position);
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String title = currentBook.getTitle();
        titleView.setText(title);
        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        String author = currentBook.getAuthor();
        authorView.setText(author);
        return listItemView;
    }
}