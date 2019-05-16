package com.example.mvvm_todo_app_impl;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskViewModel mTaskViewModel;
    public static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button nt_button = findViewById(R.id.nt_button);
        nt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);
            }
        });

        recyclerView=(RecyclerView) findViewById(R.id.task_recyclerView);
        final TaskAdapter mTaskAdapter = new TaskAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mTaskAdapter);




        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        mTaskViewModel.getAllToDoTask().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                mTaskAdapter.setTasks(tasks);
            }
        });
    }
        public void onActivityResult( int requestCode, int resultCode, Intent data){
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
                Task task = new Task(data.getStringExtra(NewTaskActivity.EXTRA_TASK));
                mTaskViewModel.insert(task);
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Hey",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
