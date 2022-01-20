package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainPrzypominajka extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_przypominajka);

        Button buttonAddReminder = (Button) findViewById(R.id.addReminder);
        Button buttonActualReminder = (Button) findViewById(R.id.actualReminder);
        Button buttonArchivalReminder = (Button) findViewById(R.id.archivalReminder);

        buttonAddReminder.setOnClickListener(this);
        buttonActualReminder.setOnClickListener(this);
        buttonArchivalReminder.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.addReminder:
                Intent intentAddReminder=new Intent(this,MainAddReminder.class);
                startActivity(intentAddReminder);
                break;
            case R.id.actualReminder:
                Intent intentActualReminder=new Intent(this,MainActualReminder.class);
                startActivity(intentActualReminder);
                break;
            case R.id.archivalReminder:
                Intent intentArchivalReminder=new Intent(this,MainArchivalReminder.class);
                startActivity(intentArchivalReminder);
                break;
        }


    }
}