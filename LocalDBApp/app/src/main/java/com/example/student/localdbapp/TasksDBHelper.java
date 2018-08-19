package com.example.student.localdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TasksDBHelper extends SQLiteOpenHelper {
    private static TasksDBHelper taskDb;
    private static final String DB_NAME = "task_db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_TITLE = "title";
    private static final String CREATE_TABLE_COMMAND =
            "CREATE TABLE %s " +
                    "(" + " %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s DATETIME DEFAULT CURRENT_TIMESTAMP);";

    private static final String DROP_TABLE_COMMAND = " DROP TABLE IF EXISTS " +
            TABLE_NAME;

    private TasksDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static TasksDBHelper getHelper(Context context) {

        if (taskDb == null) {

            taskDb = new TasksDBHelper(context);
        }
        return taskDb;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL
                (String.format
                        (CREATE_TABLE_COMMAND, TABLE_NAME, COLUMN_ID, COLUMN_TITLE, COLUMN_DATE));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE_COMMAND);

//create new one

        onCreate(db);

    }

    public long insertTask(String task, long timestamp) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task);
        values.put(COLUMN_DATE, timestamp);
        final long id = database.insert(TABLE_NAME, null, values);
        database.close();
        return id;
    }

    public long insertTask(String task) {
        final SQLiteDatabase database = this.getWritableDatabase();
        final ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task);
        final long id = database.insert(TABLE_NAME, null, values);
        database.close();
        return id;
    }

    public Task getTask(long id) {
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(TABLE_NAME, new String[]
                        {COLUMN_ID, COLUMN_ID, COLUMN_DATE}, COLUMN_ID + "=?",
                new String[]{String.valueOf(id)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            return null;
        }
        final long taskid = cursor.getLong(cursor.getColumnIndex(COLUMN_ID));
        final String taskTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
        final long timaetamp = cursor.getLong(cursor.getColumnIndex(COLUMN_DATE));
        cursor.close();
        return new Task(taskTitle,taskid,timaetamp);

    }

    public int getTasksCount() {

        final String query = "SELECT * FROM " + TABLE_NAME;
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.rawQuery(query, null);
        final int count = cursor.getCount();
        cursor.close();
        return count;


    }


}
