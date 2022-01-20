package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActualReminder extends AppCompatActivity {
    DataBaseRemind db = new DataBaseRemind(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_actual_reminder);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.actualReminderRecyclerView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Remind> remind = new ArrayList<>();

        remind=db.showUpcomingRemind();

        recyclerView.setAdapter(new Myadapter1(remind, recyclerView));

    }
}