package com.example.user.todo_list_project;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user on 06/09/2016.
 */
public class TaskView extends AppCompatActivity {

    TextView mTaskName;
    TextView mTaskId;
    Context context = this;
    MyDBHandler dbHandler = new MyDBHandler(context);
    EditText mTaskNotes;
    Button mEditButton;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_page);

        mTaskName = (TextView)findViewById(R.id.taskHeader);
        mTaskId = (TextView)findViewById(R.id.taskID);
        mTaskNotes = (EditText)findViewById(R.id.taskNote);
        mEditButton = (Button)findViewById(R.id.editButton);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        String taskId = extras.getString("task_id");
        Log.d("TaskView", taskId);

        Long ID = Long.valueOf(taskId);
        Log.d("TaskView", Long.toString(ID));

        Task task =  dbHandler.findByID(ID);

        mTaskName.setText(task.getTaskName());
        mTaskId.setText(Long.toString(task.getTaskID()));
        mTaskNotes.setText(task.getTaskNote());

        mEditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Task task = new Task(mTaskName.getText().toString());
                task.setTaskID(Long.valueOf(mTaskId.getText().toString()));
                task.setTaskNote(mTaskNotes.getText().toString());

                dbHandler.update(task);

                Toast.makeText(context, "Task Updated!", Toast.LENGTH_SHORT).show();
            }
        });



    }




}
