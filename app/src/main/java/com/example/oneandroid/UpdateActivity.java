package com.example.oneandroid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    TextView title_view, description_view;
    Button edit_button;
    private Context context;


    String id, title, value;
    Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.context = context;
        this.activity = activity;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_view = findViewById(R.id.titleView);
        description_view = findViewById(R.id.descView);


        edit_button = findViewById(R.id.editButt);



        getAndSetIntentData();


        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }




        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateActivity.this, EditActivity.class);
                intent.putExtra("idEdit", id);
                intent.putExtra("titleEdit", title);
                intent.putExtra("valueEdit", value);
                startActivityForResult(intent,1);
                finish();

            }
        });


    }



    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("value") ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            value = getIntent().getStringExtra("value");

            //Setting Intent Data
            title_view.setText(title);
            description_view.setText(value);
            Log.d("dataGot", title+" "+value);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }


}