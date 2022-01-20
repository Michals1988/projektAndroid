package com.example.projektandroid;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;



public class MainShowNotes extends Activity {

    ListView listView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_notes);

        listView = (ListView) findViewById(R.id.allNotes);

        Context context = getApplicationContext();
        String folder = context.getFilesDir().getAbsolutePath() + File.separator + "notes";

        File subFolder = new File(folder);

        String[] values = subFolder.list();

       final  ArrayList<String> arrayList=new ArrayList<String>(Arrays.asList(values));

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, arrayList);


        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String  itemValue    = (String) listView.getItemAtPosition(position);


                String dir = getFilesDir().getAbsolutePath();
                File fileToDelete = new File(dir, itemValue);
                boolean deleted = fileToDelete.delete();

                arrayList.remove(position);
                adapter.notifyDataSetChanged();


                Toast.makeText(getApplicationContext(), "  Notatka : " +itemValue +" została usunięta", Toast.LENGTH_LONG).show();


                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                int itemPosition     = position;
                String  itemValue    = (String) listView.getItemAtPosition(position);
                Intent intentEditNote=new Intent(MainShowNotes.this,MainEditNote.class);

                intentEditNote.putExtra("nameOfFileToRead",itemValue);
                startActivity(intentEditNote);

            }

        });
    }
}