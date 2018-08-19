package com.example.student.localdbapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textCount;
    private Button insert, getcount;
    private EditText editDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TasksDBHelper tasksDBHelper = TasksDBHelper.getHelper(this);
        tasksDBHelper.getTasksCount();
        getFind();
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksDBHelper.insertTask(editDb.getText().toString());
            }
        });


getcount.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

       textCount.setText(String.valueOf(tasksDBHelper.getTasksCount()));
    }
});
    }


    public void getFind() {
        textCount = findViewById(R.id.text_view);
        insert = findViewById(R.id.insert_button);
        getcount = findViewById(R.id.get_count_button);
        editDb = findViewById(R.id.edit_text);

    }
}
