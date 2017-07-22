package com.example.android.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by danok on 7/16/2017.
 */

public class HelperHabbit extends SQLiteOpenHelper {

    //    name of te database
    private static final String DATABASE_NAME = "habbit.db";

    //    just in case we upgrade the db (we won't :))
    private static final int DATABASE_VERSION = 1;

    //    the constructor for the class
    public HelperHabbit(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_HABBITS_TABLE = "CREATE TABLE " + HabitContract.HabbitEntry.TABLE_NAME + " ("
                + HabitContract.HabbitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HabitContract.HabbitEntry.COLUMN_WEEKDAY_NAME + " TEXT NOT NULL, "
                + HabitContract.HabbitEntry.COLUMN_RUNNING + " INTEGER, "
                + HabitContract.HabbitEntry.COLUMN_PUSHUPS + " INTEGER, "
                + HabitContract.HabbitEntry.COLUMN_MOOD + " MOOD NOT NULL); ";

        db.execSQL(SQL_CREATE_HABBITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //        NOTHING TO BE DONE HERE
    }
}
