package com.example.mvvm_todo_app_impl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditTaskActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        editText = findViewById(R.id.edit_task);
        button = findViewById(R.id.add_task_btn);

        Intent intent = getIntent();

        if (intent.hasExtra(AppConstants.EXTRA_ID)) {
            editText.setText(intent.getStringExtra(AppConstants.EXTRA_TASK));
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = editText.getText().toString();

                if (task.trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Insert a task", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra(AppConstants.EXTRA_TASK, task);

                int id = getIntent().getIntExtra(AppConstants.EXTRA_ID, -1);
                if (id != -1) {
                    intent.putExtra(AppConstants.EXTRA_ID, id);
                }

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
