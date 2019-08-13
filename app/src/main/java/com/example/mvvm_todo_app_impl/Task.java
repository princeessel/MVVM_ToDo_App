package com.example.mvvm_todo_app_impl;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "task_table")
public class Task {



    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    @ColumnInfo(name="task")
    private String mTask;

    @ColumnInfo(name="isTaskCompleted")
    private boolean isTaskCompleted;

    public boolean isTaskCompleted() {
        return isTaskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        isTaskCompleted = taskCompleted;
    }

    private boolean isSelected;

    public Task(String task){
        this.mTask=task;
    }

    public String getTask(){
        return this.mTask;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
