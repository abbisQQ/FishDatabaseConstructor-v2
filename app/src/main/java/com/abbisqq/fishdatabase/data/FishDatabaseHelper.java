package com.abbisqq.fishdatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chart on 27/5/2017.
 */

public class FishDatabaseHelper extends SQLiteOpenHelper {



    private static final String DATABASE_NAME = "fish_manager.db";


    private static final int DATABASE_VERSION = 6;



    public FishDatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null , DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_WEATHER_TABLE = "CREATE TABLE "+

                FishDatabaseContract.TasksEntry.TABLE_NAME + " (" +
                FishDatabaseContract.TasksEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                FishDatabaseContract.TasksEntry.NAME + " TEXT NOT NULL, " +
                FishDatabaseContract.TasksEntry.SIZE + " TEXT NOT NULL, "+
                FishDatabaseContract.TasksEntry.IMAGE + " TEXT NOT NULL, "+
                FishDatabaseContract.TasksEntry.REVIEW + " TEXT NOT NULL);";



        db.execSQL(SQL_CREATE_WEATHER_TABLE);



    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + FishDatabaseContract.TasksEntry.TABLE_NAME);
        onCreate(db);

    }
}
