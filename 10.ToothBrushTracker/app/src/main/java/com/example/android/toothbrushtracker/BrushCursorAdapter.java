package com.example.android.toothbrushtracker;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by danok on 7/18/2017.
 */

public class BrushCursorAdapter extends CursorAdapter {
    private int quantity;
    private int row_id;

    public BrushCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views that we want to modify in the list item layout

        TextView brandTextView = (TextView) view.findViewById(R.id.list_brand);
        TextView quantityTextView = (TextView) view.findViewById(R.id.list_quantity);
        ImageView imgImageView = (ImageView) view.findViewById(R.id.image_list);
        TextView priceTextView = (TextView) view.findViewById(R.id.list_price);

        final int id = cursor.getColumnIndex(BrushContract.BrushEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_NAME);
        int brandColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_BRAND);
        final int quantityColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_QUANTITY);
        int imageColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_IMAGE_BLOB);

        int priceColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_PRICE);

        final int row_id = cursor.getInt(id);
        final String brushName = cursor.getString(nameColumnIndex);
        final String brushBrand = cursor.getString(brandColumnIndex);
        final String brushQuantity = cursor.getString(quantityColumnIndex);
        final byte[] brushImage = cursor.getBlob(imageColumnIndex);

        final String brushPrice = cursor.getString(priceColumnIndex);

        brandTextView.setText(brushBrand);
        quantityTextView.setText(brushQuantity);

        priceTextView.setText(brushPrice);
        final Uri uri = ContentUris.withAppendedId(BrushContract.BrushEntry.CONTENT_URI, cursor.getInt(cursor.getColumnIndexOrThrow(BrushContract.BrushEntry._ID)));

        TextView saleButton = (TextView) view.findViewById(R.id.sale);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int m = Integer.parseInt(brushQuantity);
                if (m > 0) {
                    m = m - 1;
                    ContentValues values = new ContentValues();
                    values.put(BrushContract.BrushEntry.COLUMN_QUANTITY, m);
                    context.getContentResolver().update(uri, values, null, null);
                }
            }
        });
    }
}