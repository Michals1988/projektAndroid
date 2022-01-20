package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.view.View;


import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonPrzypominajka = (Button) findViewById(R.id.button10);
        Button buttonKsiazkaTelefoniczna = (Button) findViewById(R.id.button11);
        Button buttonNotatki = (Button) findViewById(R.id.button12);
        Button buttonMultimedia = (Button) findViewById(R.id.button13);

        buttonPrzypominajka.setOnClickListener(this);
        buttonKsiazkaTelefoniczna.setOnClickListener(this);
        buttonNotatki.setOnClickListener(this);
        buttonMultimedia.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button10:
                Intent intentPrzypominajka=new Intent(this,MainPrzypominajka.class);
                startActivity(intentPrzypominajka);
                break;
            case R.id.button11:
                Intent intentKsiazkaTelefoniczna=new Intent(this,MainKsiazkaTelefoniczna.class);
                startActivity(intentKsiazkaTelefoniczna);
                break;
            case R.id.button12:
                Intent intentNotatnik=new Intent(this,MainNotatnik.class);
                startActivity(intentNotatnik);
                break;
            case R.id.button13:
                Intent intentMultimedia=new Intent(this,MainMultimedia.class);
                startActivity(intentMultimedia);
                break;
        }

    }
}