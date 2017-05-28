package com.abbisqq.fishdatabase.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static com.abbisqq.fishdatabase.data.FishDatabaseContract.TasksEntry.TABLE_NAME;

/**
 * Created by chart on 27/5/2017.
 */

public class FishDatabaseContentProvider extends ContentProvider {




    public static final int TASK_CODE =100;


    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private FishDatabaseHelper mOpenHelper;



    public static UriMatcher buildUriMatcher() {
        // Initialize a UriMatcher with no matches by passing in NO_MATCH to the constructor
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(FishDatabaseContract.CONTENT_AUTHORITY,FishDatabaseContract.TASK_PATH,TASK_CODE);

        return uriMatcher;
    }



    @Override
    public boolean onCreate() {
        mOpenHelper = new FishDatabaseHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        Cursor cursor;

        switch (sUriMatcher.match(uri)){

            case TASK_CODE: {

                cursor = mOpenHelper.getReadableDatabase().query(
                        TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        sortOrder);

                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }




    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();


        int match = sUriMatcher.match(uri);
        Uri returnUri; // URI to be returned

        switch (match) {
            case TASK_CODE:

                // Inserting values into tasks table
                long id = db.insert(TABLE_NAME, null, values);
                if ( id > 0 ) {
                    returnUri = ContentUris.withAppendedId(FishDatabaseContract.TasksEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                }
                break;

            // Default case throws an UnsupportedOperationException
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        int tasksDeleted;

        // Get the task ID from the URI path
        String id = uri.getPathSegments().get(1);
        // Use selections/selectionArgs to filter for this ID
        tasksDeleted = db.delete(TABLE_NAME, "_id=?", new String[]{id});


        if (tasksDeleted != 0) {
            // A task was deleted, set notification
            getContext().getContentResolver().notifyChange(uri, null);
        }

        // Return the number of tasks deleted
        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
