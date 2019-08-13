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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.task_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TaskAdapter mTaskAdapter = new TaskAdapter(getApplicationContext());
        recyclerView.setAdapter(mTaskAdapter);

        mTaskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        mTaskViewModel.getAllToDoTask().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> tasks) {
                mTaskAdapter.setTasks(tasks);
            }
        });

        Button nt_button = findViewById(R.id.nt_button);
        nt_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
                startActivityForResult(intent, AppConstants.ADD_TASK_REQUEST_CODE);
            }
        });


        mTaskAdapter.setOnItemClickListener(new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Intent intent = new Intent(MainActivity.this, AddEditTaskActivity.class);
                intent.putExtra(AppConstants.EXTRA_ID, task.getId());
                intent.putExtra(AppConstants.EXTRA_TASK, task.getTask());
                startActivityForResult(intent, AppConstants.EDIT_TASK_REQUEST_CODE);

            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AppConstants.ADD_TASK_REQUEST_CODE && resultCode == RESULT_OK) {
            String tasks = data.getStringExtra(AppConstants.EXTRA_TASK);
            Task task = new Task(tasks);
            mTaskViewModel.insert(task);

            Toast.makeText(getApplicationContext(), "Task Saved", Toast.LENGTH_LONG).show();
        } else if (requestCode == AppConstants.EDIT_TASK_REQUEST_CODE && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AppConstants.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getApplicationContext(), "Task Can't be Updated", Toast.LENGTH_LONG).show();
                return;
            }

            String tasks = data.getStringExtra(AppConstants.EXTRA_TASK);
            Task task = new Task(tasks);
            task.setId(id);
            mTaskViewModel.update(task);

            Toast.makeText(getApplicationContext(), "Task Updated", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getApplicationContext(), "Task Not Saved", Toast.LENGTH_LONG).show();
        }
    }
}