package com.example.user.todo_list_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */
public class MainActivity extends AppCompatActivity {

    ListView lvTasks;
    ArrayList<Task> items;
    ArrayAdapter<Task> itemsAdapter;
    UserCursorAdapter userCursorAdapter;
    Context context = this;
    MyDBHandler dbHandler = new MyDBHandler(context);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

//        dbHandler.onUpgrade(dbHandler.getWritableDatabase(), 1, 2);

        lvTasks = (ListView) findViewById(R.id.listView);

        Cursor taskCursor = dbHandler.getAll();

        userCursorAdapter = new UserCursorAdapter(this, taskCursor);

        setupListViewListener();

        lvTasks.setAdapter(userCursorAdapter);

    }

    public void onAddItem(View view) {
        EditText newItem = (EditText) findViewById(R.id.submitText);
        String itemText = newItem.getText().toString();
        Task task = new Task(itemText);
        task.setTaskID(dbHandler.addTask(task));
        Log.d("ToDo", Long.toString(task.getTaskID()));
        Cursor newCursor = dbHandler.getAll();
        userCursorAdapter.changeCursor(newCursor);
        newItem.setText("");
    }

    private void setupListViewListener() {
        lvTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, TaskView.class);

//                Log.d("itemClick", "" + view.getId());
//                Log.d("itemClick", "" + id);
//                intent.putExtra("Task_id", random );

                Cursor taskCursor = (Cursor) parent.getItemAtPosition(position);

                Long taskId = taskCursor.getLong(taskCursor.getColumnIndexOrThrow("_id"));
                String taskName = taskCursor.getString(taskCursor.getColumnIndexOrThrow("Title"));

                Log.d("ToDo ", taskName);
                Log.d("ToDo", Long.toString( taskId ) );

                intent.putExtra("task_name", taskName );
                intent.putExtra("task_id", Long.toString(taskId));
                startActivity(intent);
            }
        });


    }
}



