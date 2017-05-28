package com.abbisqq.fishdatabase.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by chart on 27/5/2017.
 */

public class FishDatabaseContract {

    public static final String CONTENT_AUTHORITY = "com.abbisqq.fishdatabase";


    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    public static final String TASK_PATH = "tasks";


    public static final class TasksEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TASK_PATH)
                .build();


        public static final String TABLE_NAME = "fish_table";

        public static final String NAME = "name";

        public static final String SIZE = "size";

        public static final String REVIEW = "review";

        public static final String IMAGE = "image";


    }
}








