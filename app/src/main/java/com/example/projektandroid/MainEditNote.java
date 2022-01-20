package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class MainEditNote extends AppCompatActivity implements View.OnClickListener {

    EditText textName, textNote;

    String name = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit_note);

        String file = getIntent().getStringExtra("nameOfFileToRead");

        String FOLDERNAME = "notes";

        Context context = getApplicationContext();
        String folder = context.getFilesDir().getAbsolutePath() + File.separator + FOLDERNAME;

        File subFolder = new File(folder);

        FileInputStream fos = null;


        Button buttonSubmit = (Button) findViewById(R.id.button8);
        buttonSubmit.setOnClickListener(this);

        Button buttonDiscard = (Button) findViewById(R.id.button7);
        buttonDiscard.setOnClickListener(this);

        textName = (EditText) findViewById(R.id.textName);
        textNote = (EditText) findViewById(R.id.textNote);

        name = file.replace(".txt", "");
        textName.setText(name);


        try {
            fos = new FileInputStream(new File(subFolder, file));

            int c;
            String temp = "";
            while ((c = fos.read()) != -1) {
                temp = temp + (char) c;
            }
            textNote.setText(temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button7:
                Intent intentNotatnik = new Intent(this, MainNotatnik.class);
                startActivity(intentNotatnik);
                break;
            case R.id.button8:
                String text = textNote.getText().toString();
                String noteName = textName.getText().toString() + ".txt";
                FileOutputStream fos = null;
                try {
                    fos = openFileOutput(noteName, MODE_PRIVATE);
                    fos.write(text.getBytes());

                    textNote.getText().clear();
                    textName.getText().clear();
                    Toast.makeText(this, "Notatka zapisana pomyślnie", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                Intent intentNotatnik2 = new Intent(this, MainNotatnik.class);
                startActivity(intentNotatnik2);
                break;
        }
    }
}


