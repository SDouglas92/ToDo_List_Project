package com.example.user.todo_list_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by user on 05/09/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper implements BaseColumns {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "Task.db";

    private static final String TABLE_NAME = "Tasks";
    private static final String COLUMN_TITLE = "Title";
    private static final String Column_NOTES = "Notes";
    private static final String Create_Db_Entries = "CREATE TABLE"+ TABLE_NAME+ "(ID INTEGER PRIMARY KEY,"+ COLUMN_TITLE+ "VARCHAR(255),"+ Column_NOTES+ "TEXT);";
    private static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(Create_Db_Entries);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_ENTRIES);
        onCreate(db);
    }

    public long addTask(Task task){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTaskName());
        values.put(Column_NOTES, task.getTaskNote());

        SQLiteDatabase db = this.getWritableDatabase();
        long rowID = db.insert(TABLE_NAME, null, values);
        db.close();

        return rowID;

    }

}
