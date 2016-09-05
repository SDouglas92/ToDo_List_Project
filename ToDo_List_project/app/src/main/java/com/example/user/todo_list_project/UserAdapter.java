package com.example.user.todo_list_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by user on 05/09/2016.
 */
public class UserAdapter extends ArrayAdapter<Task> {

    public UserAdapter(Context context, ArrayList<Task> tasks){
        super(context, 0, tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Task task = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.task_user, parent, false);
        }

        TextView taskName = (TextView)convertView.findViewById(R.id.taskName);

        taskName.setText(task.getTaskName());

        return convertView;
    }
}
