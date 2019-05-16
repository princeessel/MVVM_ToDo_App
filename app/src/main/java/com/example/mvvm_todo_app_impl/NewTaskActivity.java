package com.example.mvvm_todo_app_impl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {
    private EditText editText;
    private Button button;
    public static final String EXTRA_TASK = "com.example.mvvm_todo_app_impl.TASK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        editText=findViewById(R.id.edit_task);
        button=findViewById(R.id.add_task_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (TextUtils.isEmpty(editText.getText())) {
                    setResult(RESULT_CANCELED, intent);
                } else {
                    String task = editText.getText().toString();
                    intent.putExtra(EXTRA_TASK, task);
                    setResult(RESULT_OK, intent);
                }
                finish();
            }

        });
    }
}
