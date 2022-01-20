package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.File;

public class MainAddNote extends AppCompatActivity implements View.OnClickListener {

EditText textName, textNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_note);

        Button buttonSubmit=(Button) findViewById(R.id.button8);
        buttonSubmit.setOnClickListener(this);

        Button buttonDiscard=(Button) findViewById(R.id.button7);
        buttonDiscard.setOnClickListener(this);

        textName=(EditText)findViewById(R.id.textName);
        textNote=(EditText)findViewById(R.id.textNote);


    }

    @Override
    public void onClick(View v) {

        String FOLDERNAME = "notes";

        Context context = getApplicationContext();
        String folder = context.getFilesDir().getAbsolutePath() + File.separator + FOLDERNAME;

        File subFolder = new File(folder);

        if (!subFolder.exists()) {
            subFolder.mkdirs();
        }

        switch(v.getId()){
            case R.id.button7:
                Intent intentNotatnik=new Intent(this,MainNotatnik.class);
                startActivity(intentNotatnik);
                break;
            case R.id.button8:
                    String text=textNote.getText().toString();
                    String noteName=textName.getText().toString()+".txt";
                    FileOutputStream fos=null;
                try {
                    fos=new FileOutputStream(new File(subFolder, noteName));
                    fos.write(text.getBytes());

                    textNote.getText().clear();
                    textName.getText().clear();
                    Toast.makeText(this,"Notatka zapisana pomyślnie", Toast.LENGTH_LONG).show();
                }
                catch(FileNotFoundException e) {
                e.printStackTrace();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
                finally{
                    if (fos!=null){
                        try {
                            fos.close();
                        }
                        catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
                Intent intentNotatnik2=new Intent(this,MainNotatnik.class);
                startActivity(intentNotatnik2);
                break;
        }
    }
}

