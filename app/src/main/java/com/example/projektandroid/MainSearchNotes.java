package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MainSearchNotes extends AppCompatActivity implements View.OnClickListener {

    Button buttonSearch;
    ListView listView;
    EditText searchNote;

    ArrayAdapter<String> adapter;
    String[] values;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_notes);

        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(this);

        searchNote = (EditText) findViewById(R.id.searchNote);

        listView = (ListView) findViewById(R.id.searchNoteList);

        Context context = getApplicationContext();
        String folder = context.getFilesDir().getAbsolutePath() + File.separator + "notes";

        File subFolder = new File(folder);

        values = subFolder.list();

        arrayList = new ArrayList<String>(Arrays.asList(values));

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);

        listView.setAdapter(adapter);



    }

    @Override
    public void onClick(View v) {

        ArrayList<String> findMatches=new ArrayList<String>();

        for(int i=0; i<arrayList.size();i++){
            int find=-2;
            find=arrayList.get(i).indexOf(searchNote.getText().toString());
            if (find>=0){
                findMatches.add(arrayList.get(i));
            }
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, findMatches);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {


            int itemPosition     = position;
            String  itemValue    = (String) listView.getItemAtPosition(position);
            Intent intentEditNote=new Intent(MainSearchNotes.this,MainEditNote.class);

            intentEditNote.putExtra("nameOfFileToRead",itemValue);
            startActivity(intentEditNote);

        }

        });
    }
}