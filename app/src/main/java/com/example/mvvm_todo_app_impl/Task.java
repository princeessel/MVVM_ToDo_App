package com.example.mvvm_todo_app_impl;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "task_table")
public class Task {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name="task")
    private String mTask;

    public Task(String task){
        this.mTask=task;
    }

    public String getTask(){
        return this.mTask;
    }
}
