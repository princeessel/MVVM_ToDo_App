package com.example.mvvm_todo_app_impl;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;


@Database(entities ={Task.class}, version= 4, exportSchema =false)
public abstract class TaskRoomDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
    private static TaskRoomDatabase INSTANCE;

    public static TaskRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TaskRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),TaskRoomDatabase.class, "task_db")
                                  .fallbackToDestructiveMigration()
                                  .addCallback(sRoomDatabaseCallback)
                                  .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback= new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    //    populate database in background
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        private final TaskDao mTaskDao;
        String[] tasks= {};
        PopulateDbAsync(TaskRoomDatabase db){
            mTaskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            mTaskDao.deleteAll();

            for(int i=0; i<= tasks.length-1; i++){
                Task task= new Task(tasks[i]);
                mTaskDao.insert(task);
            }
            return null;
        }
    }
}