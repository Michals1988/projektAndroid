package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainNotatnik extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_notatnik);

        Button buttonAddNote = (Button) findViewById(R.id.button2);
        Button buttonSearchNote = (Button) findViewById(R.id.button3);
        Button buttonShowNotes = (Button) findViewById(R.id.button4);

        buttonAddNote.setOnClickListener(this);
        buttonSearchNote.setOnClickListener(this);
        buttonShowNotes.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button2:
                Intent intent2 = new Intent(this, MainAddNote.class);
                startActivity(intent2);
                break;
            case R.id.button3:
                Intent intent3 = new Intent(this, MainShowNotes.class);
                startActivity(intent3);
                break;
            case R.id.button4:
                Intent intent4 = new Intent(this, MainSearchNotes.class);
                startActivity(intent4);

        }
    }
}



