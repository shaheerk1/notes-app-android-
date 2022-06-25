package com.example.oneandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addActivity extends AppCompatActivity {

    EditText textTitle, textValue;
    Button saveButton;

    MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        textTitle = findViewById(R.id.textTitle);
        textValue = findViewById(R.id.textValue);
        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper myDB = new dbHelper(addActivity.this);
                myDB.addBook(textTitle.getText().toString().trim(),
                        textValue.getText().toString().trim());
                        mainActivity = new MainActivity();

            }
        });
    }
}