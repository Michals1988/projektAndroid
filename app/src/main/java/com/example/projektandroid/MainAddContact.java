package com.example.projektandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainAddContact extends AppCompatActivity implements View.OnClickListener {

    EditText addContactName, addContactSurname, addContactEmail, addContactNumber;
    RadioGroup gender;
    RadioButton genderChosen, genderFemale, avatarFemale, genderMale, avatarMale;
    RadioGroup avatar;
    RadioButton avatarChosen;
    DataBaseContacts db = new DataBaseContacts(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_contact);

        addContactName = (EditText) findViewById(R.id.addContactName);
        addContactSurname = (EditText) findViewById(R.id.addContactSurname);
        addContactEmail = (EditText) findViewById(R.id.addContactEmail);
        addContactNumber = (EditText) findViewById(R.id.addContactNumber);

        avatar = (RadioGroup) findViewById(R.id.avatarGroup);
        gender = (RadioGroup) findViewById(R.id.addContactGender);

        genderFemale=(RadioButton) findViewById(R.id.addContactFemale);
        genderFemale.setOnClickListener(this);

        genderMale=(RadioButton) findViewById(R.id.addContactMale);
        genderMale.setOnClickListener(this);

        Button buttonSubmit = (Button) findViewById(R.id.addContactAccept);
        buttonSubmit.setOnClickListener(this);

        Button buttonDiscard = (Button) findViewById(R.id.addContactDiscard);
        buttonDiscard.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addContactFemale:
                avatarFemale =(RadioButton)findViewById(R.id.avatar2);
                avatarFemale.setChecked(true);
                break;

            case R.id.addContactMale:
                avatarMale =(RadioButton)findViewById(R.id.avatar1);
                avatarMale.setChecked(true);
                break;

            case R.id.addContactAccept:
                Contact contact = new Contact();

                if(addContactName.getText().toString().equals("")||addContactSurname.getText().toString().equals("")||addContactNumber.getText().toString().equals("")){
                    break;}


                contact.setImie(addContactName.getText().toString());
                contact.setNazwisko(addContactSurname.getText().toString());
                contact.setEmail(addContactEmail.getText().toString());
                contact.setNumerTelefonu(Integer.parseInt(addContactNumber.getText().toString()));


                int selectedIdGender = gender.getCheckedRadioButtonId();
                genderChosen = (RadioButton) findViewById(selectedIdGender);
                contact.setPlec(genderChosen.getText().toString());


                int selectedIdAvatar = avatar.getCheckedRadioButtonId();
                avatarChosen = (RadioButton) findViewById(selectedIdAvatar);

                switch (selectedIdAvatar){
                    case 2131230803:
                        contact.setAvatar("Avatar1");
                        break;
                    case 2131230804:
                        contact.setAvatar("Avatar2");
                        break;
                    case 2131230805:
                        contact.setAvatar("Avatar3");
                        break;
                    case 2131230806:
                        contact.setAvatar("Avatar4");
                        break;
                    default:
                        contact.setAvatar("Avatar1");
                }


                db.addContact(contact);

                Toast.makeText(this, "Kontakt został zapisany!", Toast.LENGTH_LONG).show();

                Intent intentBackToKsiazka=new Intent(this, MainKsiazkaTelefoniczna.class);
                startActivity(intentBackToKsiazka);

                break;

            case R.id.addContactDiscard:

                Toast.makeText(this, "Nie zapisano kontaktu!", Toast.LENGTH_LONG).show();

                Intent intentBackToKsiazka2=new Intent(this, MainKsiazkaTelefoniczna.class);
                startActivity(intentBackToKsiazka2);
                break;
        }
    }
}