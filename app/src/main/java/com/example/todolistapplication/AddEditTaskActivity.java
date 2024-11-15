package com.example.todolistapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddEditTaskActivity extends AppCompatActivity {

    private EditText editTextTaskName;
    private DatabaseHelper databaseHelper;
    private int taskId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        editTextTaskName = findViewById(R.id.editTextTaskName);
        Button buttonSave = findViewById(R.id.buttonSave);
        databaseHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("taskId")) {
            taskId = intent.getIntExtra("taskId", -1);
            editTextTaskName.setText(intent.getStringExtra("taskName"));
        }

        buttonSave.setOnClickListener(v -> {
            String taskName = editTextTaskName.getText().toString().trim();
            if (!taskName.isEmpty()) {
                if (taskId == -1) {
                    databaseHelper.addTask(taskName);
                    Toast.makeText(this, "Task added", Toast.LENGTH_SHORT).show();
                } else {
                    databaseHelper.updateTask(taskId, taskName);
                    Toast.makeText(this, "Task updated", Toast.LENGTH_SHORT).show();
                }
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(this, "Please enter a task name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
