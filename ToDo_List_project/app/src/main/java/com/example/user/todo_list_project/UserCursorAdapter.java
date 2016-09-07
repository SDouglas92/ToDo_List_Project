package com.example.user.todo_list_project;

import android.content.Context;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by user on 06/09/2016.
 */
public class UserCursorAdapter extends CursorAdapter {

    public UserCursorAdapter(Context context, Cursor cursor){
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        return LayoutInflater.from(context).inflate(R.layout.task_cursor_view, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView cursorNameView = (TextView)view.findViewById(R.id.cursorNameView);
        TextView cursorIdView = (TextView)view.findViewById(R.id.cursorIdView);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("Title"));
        Long id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));


        cursorNameView.setText(name);
        cursorIdView.setText(Long.toString(id));

    }
}
