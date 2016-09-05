package com.example.user.todo_list_project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        lvTasks = (ListView)findViewById(R.id.listView);
        items = new ArrayList<Task>();
        itemsAdapter = new ArrayAdapter<Task>(this, android.R.layout.simple_list_item_1, items);

        setupListViewListener();

        lvTasks.setAdapter(itemsAdapter);

    }

    public void onAddItem(View view){
        EditText newItem = (EditText)findViewById(R.id.submitText);
        String itemText = newItem.getText().toString();
        Task task = new Task(itemText);
        itemsAdapter.add(task);
        newItem.setText("");
    }

    private void setupListViewListener(){
        lvTasks.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View item, final int pos, long id) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Delete Confirmation");
                alertDialogBuilder.setMessage("Are You Sure ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                items.remove(pos);
                                itemsAdapter.notifyDataSetChanged();

                                 }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();




                return true;
            }
        });
    }


}
