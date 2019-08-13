package com.example.mvvm_todo_app_impl;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


public class TaskRepository {
    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTaskToDo;

    public TaskRepository(Application application) {
        TaskRoomDatabase db=TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTaskToDo = mTaskDao.getAllToDoTask();
    }

    LiveData<List<Task>>getAllToDoTask(){
        return mAllTaskToDo;
    }

    public void insert(Task task){
        new insertAsyncTask(mTaskDao).execute(task);
    }


    private class insertAsyncTask extends AsyncTask<Task,Void, Void>{
        private TaskDao mAsyncTaskDao;
        insertAsyncTask(TaskDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(Task... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update(Task task) {
        new updateAsyncTask(mTaskDao).execute(task);
    }

    private class updateAsyncTask extends AsyncTask<Task,Void, Void>{
        private TaskDao mAsyncTaskDao;
        updateAsyncTask(TaskDao dao) {
            mAsyncTaskDao=dao;
        }

        @Override
        protected Void doInBackground(Task... params) {
            mAsyncTaskDao.update(params[0]);
            return null;
        }
    }

}
