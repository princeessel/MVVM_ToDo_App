package com.example.mvvm_todo_app_impl;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;



@Dao
public interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM task_table")
    void deleteAll();

    @Query("SELECT * from task_table ORDER BY task ASC")
    LiveData<List<Task>> getAllToDoTask();
}
