package com.example.android.toothbrushtracker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by danok on 7/18/2017.
 */

public class EditorActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_BRUSH_LOADER = 0;

    public static final int GET_FROM_GALLERY = 3;
    private Uri currentBrushUri;

    private EditText nameEdit;

    private static final int PICK_IMAGE_REQUEST = 0;

    private EditText brandEdit;

    private Uri imageURI;

    private EditText priceEdit;

    private EditText quantityEdit;

    private ImageView imageEdit;

    private boolean brushHasChanged;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            brushHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        Intent intent = getIntent();
        currentBrushUri = intent.getData();

        if (currentBrushUri == null) {
            setTitle(getString(R.string.add_a_brush));
            invalidateOptionsMenu();
        } else {
            setTitle(getString(R.string.edit_a_brush));
            getSupportLoaderManager().initLoader(EXISTING_BRUSH_LOADER, null, this);
        }

        //find relevant text edit boxes
        nameEdit = (EditText) findViewById(R.id.edit_name);
        brandEdit = (EditText) findViewById(R.id.edit_brand);
        quantityEdit = (EditText) findViewById(R.id.edit_quantity);
        priceEdit = (EditText) findViewById(R.id.edit_price);
        imageEdit = (ImageView) findViewById(R.id.image_list);

        nameEdit.setOnTouchListener(touchListener);
        brandEdit.setOnTouchListener(touchListener);
        quantityEdit.setOnTouchListener(touchListener);
        priceEdit.setOnTouchListener(touchListener);


        Button increaseBy10 = (Button) findViewById(R.id.button_increase);
        increaseBy10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m = Integer.parseInt(quantityEdit.getText().toString());
                m = m + 10;
                quantityEdit.setText(String.valueOf(m));
            }
        });

        Button orderMore = (Button) findViewById(R.id.button_order_more);
        orderMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/html");
                intent.putExtra(Intent.EXTRA_EMAIL, "theBigBrushesFactory@brushesFactory.com");
                intent.putExtra(Intent.EXTRA_SUBJECT, "Gib more brushes pls");
                intent.putExtra(Intent.EXTRA_TEXT, "Dear, Mr. Brushman, Send us some brushes we are low on  some more " +
                        nameEdit.getText().toString());

                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });

        Button decreaseBy10 = (Button) findViewById(R.id.button_decrease);
        decreaseBy10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int m = Integer.parseInt(quantityEdit.getText().toString());
                if (m >= 10) {
                    m = m - 10;
                    quantityEdit.setText(String.valueOf(m));
                }
            }
        });

        Button addImage = (Button) findViewById(R.id.image_add);
        addImage.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);
            }
        });


    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void saveBrush() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = nameEdit.getText().toString().trim();
        String brandString = brandEdit.getText().toString().trim();
        String quantityString = quantityEdit.getText().toString().trim();
        String priceString = priceEdit.getText().toString().trim();

        if (TextUtils.isEmpty(nameString)) {
            Toast.makeText(this, getString(R.string.empty_name),
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(brandString)) {
            Toast.makeText(this, getString(R.string.empty_brand),
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (Integer.parseInt(quantityString) < 0) {
            Toast.makeText(this, getString(R.string.values_quantity),
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (!isInteger(priceString)) {
            Toast.makeText(this, getString(R.string.values_price),
                    Toast.LENGTH_LONG).show();
            return;
        }

        if (imageURI == null && imageURI != Uri.parse("existing_product") ) {
            Toast.makeText(this, getString(R.string.error_image),
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (currentBrushUri == null &&
                TextUtils.isEmpty(nameString) && TextUtils.isEmpty(brandString) &&
                TextUtils.isEmpty(quantityString) && TextUtils.isEmpty(priceString)) {
            return;
        }


        int quant = 0;
        if (!TextUtils.isEmpty(quantityString)) {
            quant = Integer.parseInt(quantityString);
        }

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageURI);

        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] imageToInsertinDB = getBytes(bitmap);

        ContentValues values = new ContentValues();
        values.put(BrushContract.BrushEntry.COLUMN_NAME, nameString);
        values.put(BrushContract.BrushEntry.COLUMN_BRAND, brandString);
        values.put(BrushContract.BrushEntry.COLUMN_QUANTITY, quantityString);
        values.put(BrushContract.BrushEntry.COLUMN_PRICE, priceString);
        values.put(BrushContract.BrushEntry.COLUMN_IMAGE_BLOB, imageToInsertinDB);
        if (currentBrushUri == null) {
            Uri newUri = getContentResolver().insert(BrushContract.BrushEntry.CONTENT_URI, values);

            if (newUri == null) {
                Toast.makeText(this, getString(R.string.insert_brush_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.insert_brush_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            int rowsAffected = getContentResolver().update(currentBrushUri, values, null, null);

            if (rowsAffected == 0) {
                Toast.makeText(this, getString(R.string.insert_brush_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.insert_brush_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
            return stream.toByteArray();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Detects request codes
        if (requestCode == GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            imageURI = selectedImage;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (currentBrushUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                saveBrush();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Pop up confirmation dialog for deletion
                showDeleteConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:

                if (!brushHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!brushHasChanged) {
            super.onBackPressed();
            return;
        }
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                BrushContract.BrushEntry._ID,
                BrushContract.BrushEntry.COLUMN_NAME,
                BrushContract.BrushEntry.COLUMN_BRAND,
                BrushContract.BrushEntry.COLUMN_QUANTITY,
                BrushContract.BrushEntry.COLUMN_PRICE,
                BrushContract.BrushEntry.COLUMN_NAME,
                BrushContract.BrushEntry.COLUMN_IMAGE_BLOB,
        };

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                currentBrushUri,         // Query the content URI for the current product
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {

            int nameColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_NAME);
            int brandColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_BRAND);
            int quantityColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_QUANTITY);
            int priceColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_PRICE);
            int imageColumnIndex = cursor.getColumnIndex(BrushContract.BrushEntry.COLUMN_IMAGE_BLOB);
            // Extract out the value from the Cursor for the given column index
            String name = cursor.getString(nameColumnIndex);
            String brand = cursor.getString(brandColumnIndex);
            byte[] image = cursor.getBlob(imageColumnIndex);
            int quantity = cursor.getInt(quantityColumnIndex);
            int price = cursor.getInt(priceColumnIndex);
            // Update the views on the screen with the values from the database
            nameEdit.setText(name);
            brandEdit.setText(brand);
            quantityEdit.setText(Integer.toString(quantity));
            priceEdit.setText(Integer.toString(price));
            imageEdit.setImageBitmap(getImage(image));
            imageURI = Uri.parse("existing_product");
        }
    }

    public static Bitmap getImage(byte[] image) {
        if (image != null)
            return BitmapFactory.decodeByteArray(image, 0, image.length);
        return null;
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        nameEdit.setText("");
        brandEdit.setText("");
        quantityEdit.setText("");
        priceEdit.setText("");
    }

    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDeleteConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_message);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the brush.
                deleteBrush();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the brush.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void deleteBrush() {
        if (currentBrushUri != null) {
            int rowsDeleted = getContentResolver().delete(currentBrushUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, getString(R.string.delete_failed),
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, getString(R.string.delete_succes),
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}

