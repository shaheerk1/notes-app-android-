package com.example.oneandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    EditText title_input, description_input;

    Button update_button, delete_button ,edit_button;



    String id, title, value;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        title_input = findViewById(R.id.textTitleEdit);
        description_input = findViewById(R.id.textValueEdit);
        update_button = findViewById(R.id.updateEditButton);
        delete_button = findViewById(R.id.deleteEditButton);

        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //And only then we call this
                dbHelper myDB = new dbHelper(EditActivity.this);
                title = title_input.getText().toString().trim();
                value = description_input.getText().toString().trim();
                Log.d("dbUpdate", id +" "+title + " " + value);

                myDB.updateData(id, title,value);

                finish();

            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("idEdit") && getIntent().hasExtra("titleEdit") &&
                getIntent().hasExtra("valueEdit") ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("idEdit");
            title = getIntent().getStringExtra("titleEdit");
            value = getIntent().getStringExtra("valueEdit");

            //Setting Intent Data
            title_input.setText(title);
            description_input.setText(value);
            Log.d("dataGot", title+" "+value);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dbHelper myDB = new dbHelper(EditActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }


}