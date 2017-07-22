package com.example.android.habittracker;

import android.provider.BaseColumns;

/**
 * Created by danok on 7/16/2017.
 */

public final class HabitContract {

    //    we don't want this class to be instantiated by accident
    private HabitContract() {
    }

    public static final class HabbitEntry implements BaseColumns {
        //        name of databae
        public final static String TABLE_NAME = "habbits";

        //        unique id for the entry
        public final static String _ID = BaseColumns._ID;

        //        name of the weekday type:TEXT
        public final static String COLUMN_WEEKDAY_NAME = "name";

        //        Habit: running Type: Integer
        public final static String COLUMN_RUNNING = "running";

        //        Habit: pushups Type: Integer
        public final static String COLUMN_PUSHUPS = "pushups";

        //        Mood Type: TEXT
        public final static String COLUMN_MOOD = "mood";
    }
}
