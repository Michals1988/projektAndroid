package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainShowContacts extends AppCompatActivity {

    DataBaseContacts db = new DataBaseContacts(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_contacts);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Contact> contact = new ArrayList<>();

        contact=db.showAllContacts();

        recyclerView.setAdapter(new Myadapter(contact, recyclerView));
    }
}