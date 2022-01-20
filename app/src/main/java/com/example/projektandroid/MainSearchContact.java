package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainSearchContact extends AppCompatActivity implements View.OnClickListener{

    EditText searchText;
    Button searchButton;

    DataBaseContacts db = new DataBaseContacts(this);

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_search_contact);

        searchText=(EditText)findViewById(R.id.textSearchContact);


        searchButton=(Button)findViewById(R.id.buttonSearchContact);
        searchButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.buttonSearchContact:

                recyclerView = (RecyclerView) findViewById(R.id.recyclerviewSearchContacts);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));

                ArrayList<Contact> contact = new ArrayList<>();

                contact=db.searchContacts(searchText.getText().toString());
                recyclerView.setAdapter(new Myadapter(contact, recyclerView));
                closeKeyboard();
                break;
        }
    }

    public void closeKeyboard(){
        View view=this.getCurrentFocus();
        if(view!=null){
            InputMethodManager imm=(InputMethodManager)getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}