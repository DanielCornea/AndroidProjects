package com.example.android.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HelperHabbit dataBaseHelper = new HelperHabbit(this);
        SQLiteDatabase dataBase = dataBaseHelper.getReadableDatabase();
        insertTheInformationIntoTheDatabase();
        readTheInformationFromDatabase();
    }

    private void insertTheInformationIntoTheDatabase() {


        HelperHabbit dataBaseHelper = new HelperHabbit(this);
        SQLiteDatabase dataBase = dataBaseHelper.getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitContract.HabbitEntry.COLUMN_WEEKDAY_NAME, "Monday");
        values.put(HabitContract.HabbitEntry.COLUMN_RUNNING, 2);
        values.put(HabitContract.HabbitEntry.COLUMN_PUSHUPS, 15);
        values.put(HabitContract.HabbitEntry.COLUMN_MOOD, "happy");

        long newRowId = dataBase.insert(HabitContract.HabbitEntry.TABLE_NAME, null, values);
        Log.v("ROW INSERTED: ", String.valueOf(newRowId));
        values.put(HabitContract.HabbitEntry.COLUMN_WEEKDAY_NAME, "Tuesday");
        values.put(HabitContract.HabbitEntry.COLUMN_RUNNING, 0);
        values.put(HabitContract.HabbitEntry.COLUMN_PUSHUPS, 11);
        values.put(HabitContract.HabbitEntry.COLUMN_MOOD, "sad");

        newRowId = dataBase.insert(HabitContract.HabbitEntry.TABLE_NAME, null, values);
        Log.v("ROW INSERTED: ", String.valueOf(newRowId));

    }


    public Cursor readTheInformationFromDatabase() {

        HelperHabbit dataBaseHelper = new HelperHabbit(this);
        SQLiteDatabase dataBase = dataBaseHelper.getReadableDatabase();

        String[] projection = {
                HabitContract.HabbitEntry._ID,
                HabitContract.HabbitEntry.COLUMN_WEEKDAY_NAME,
                HabitContract.HabbitEntry.COLUMN_RUNNING,
                HabitContract.HabbitEntry.COLUMN_PUSHUPS,
                HabitContract.HabbitEntry.COLUMN_MOOD,

        };
        Cursor cursor = dataBase.query(
                HabitContract.HabbitEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        Log.v("CURSOR ROW: ", cursor.getString(cursor.getColumnIndex(HabitContract.HabbitEntry.COLUMN_MOOD)));
        cursor.moveToNext();
        Log.v("CURSOR ROW: ", cursor.getString(cursor.getColumnIndex(HabitContract.HabbitEntry.COLUMN_WEEKDAY_NAME)));

        return cursor;
    }
}

