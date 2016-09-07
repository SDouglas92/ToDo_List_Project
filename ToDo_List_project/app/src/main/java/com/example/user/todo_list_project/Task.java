package com.example.user.todo_list_project;

/**
 * Created by user on 05/09/2016.
 */
public class Task {

    private String taskName;
    private Long taskID;
    private String taskNote;


    public Task(String taskName){
        this.taskName = taskName;
        this.taskID = null;
        this.taskNote = "";

    }

    public String getTaskName(){
        return this.taskName;
    }

    public String getTaskNote(){
        return this.taskNote;
    }
     public Long getTaskID(){
         return this.taskID;
     }

    public void setTaskID(Long id){
        this.taskID = id;
    }

    public void setTaskNote(String note){
        this.taskNote = note;
    }


}
