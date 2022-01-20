package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainKsiazkaTelefoniczna extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_ksiazka_telefoniczna);

        Button buttonAddContact = (Button) findViewById(R.id.addContact);
        Button buttonShowContacts = (Button) findViewById(R.id.showContacts);
        Button buttonSearchContact= (Button) findViewById(R.id.searchContact);

        buttonAddContact.setOnClickListener(this);
        buttonShowContacts.setOnClickListener(this);
        buttonSearchContact.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.addContact:
                Intent intentAddContact=new Intent(this,MainAddContact.class);
                startActivity(intentAddContact);
                break;
            case R.id.showContacts:
                Intent intentShowContacts=new Intent(this,MainShowContacts.class);
                startActivity(intentShowContacts);
                break;
            case R.id.searchContact:
                Intent intentSearchContact=new Intent(this,MainSearchContact.class);
                startActivity(intentSearchContact);
                break;
        }

    }
}