package com.example.user.todo_list_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by user on 05/09/2016.
 */
public class MyDBHandler extends SQLiteOpenHelper implements BaseColumns {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME= "Task.db";

    private static final String TABLE_NAME = "Tasks";
    private static final String COLUMN_id = "_id";
    private static final String COLUMN_TITLE = "Title";
    private static final String Column_NOTES = "Notes";
    private static final String Create_Db_Entries = "CREATE TABLE "+ TABLE_NAME+ " ("+COLUMN_id+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN_TITLE+ " VARCHAR(255), "+ Column_NOTES+ " TEXT);";
    private static final String DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static final String ALL_ENTRIES = "SELECT * FROM "+ TABLE_NAME+";";

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

    public Long addTask(Task task){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTaskName());
        values.put(Column_NOTES, task.getTaskNote());

        SQLiteDatabase db = this.getWritableDatabase();
        Long rowID = db.insert(TABLE_NAME, null, values);
        db.close();

        return rowID;

    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor taskCursor = db.rawQuery(ALL_ENTRIES, null);



        return taskCursor;
    }

    public Task findByID(Long id){
        SQLiteDatabase db = this.getWritableDatabase();

        Log.d("DBhandler", id.toString());

        String findByID = "SELECT * FROM " + TABLE_NAME +" WHERE _id ="+id+" ;";

        Cursor taskCursor = db.rawQuery(findByID, null);
        taskCursor.moveToFirst();

        db.close();

        int columnIndex = taskCursor.getColumnIndexOrThrow("Title");
        String taskName = taskCursor.getString(columnIndex);
        Task task = new Task(taskName);

        task.setTaskID(taskCursor.getLong(taskCursor.getColumnIndexOrThrow("_id")));
        task.setTaskNote((taskCursor.getString(taskCursor.getColumnIndexOrThrow("Notes"))));



        return task;


    }

    public int update(Task task){
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, task.getTaskName());
        values.put(Column_NOTES, task.getTaskNote());

        SQLiteDatabase db = this.getWritableDatabase();

       return db.update(TABLE_NAME, values, COLUMN_id +"= ?", new String[] {Long.toString(task.getTaskID())});


    }

    public int delete(Long position){
        SQLiteDatabase db = this.getWritableDatabase();

       return db.delete(TABLE_NAME, COLUMN_id+"= ?", new String[] {Long.toString(position)});
    }

}
