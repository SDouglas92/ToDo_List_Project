package com.example.user.todo_list_project;

/**
 * Created by user on 05/09/2016.
 */


import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {

    Task task1;

    @Before
    public void before(){
       task1 = new Task("Task 1");
    }

    @Test
    public void canGetName(){
        assertEquals("Task 1", task1.getTaskName());
    }

    @Test
    public void canSetID(){
        task1.setTaskID(2);
        assertEquals(2, task1.getTaskID());
    }

    @Test
    public void canSetNote(){
        task1.setTaskNote("A Lovely Note");
        assertEquals("A Lovely Note", task1.getTaskNote());
    }
}
