package com.example.mvvm_todo_app_impl;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;


public class TaskViewModel extends AndroidViewModel {
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllToDoTask;

    public TaskViewModel(Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllToDoTask = mRepository.getAllToDoTask();
    }

    public void insert(Task task){
        mRepository.insert(task);
    }

    public void update(Task task){
        mRepository.update(task);
    }

    LiveData<List<Task>> getAllToDoTask(){
        return mAllToDoTask;
    }
}
