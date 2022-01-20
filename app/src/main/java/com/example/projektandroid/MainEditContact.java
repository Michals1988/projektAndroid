package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainEditContact extends AppCompatActivity implements View.OnClickListener{

    DataBaseContacts db=new DataBaseContacts(this);

    EditText name, surname, email, number;
    RadioButton genderButtonMale, genderButtonFemale, avatarImg, genderChosen, avatarChosen;
    RadioGroup genderGroup, avatarGroup;


    int contactId;
    Button btnDiscard, btnSave;

    Contact contact=new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit_contact);

        contactId = getIntent().getIntExtra("idContact",2);

        name=(EditText)findViewById(R.id.editContactName);
        surname=(EditText)findViewById(R.id.editContactSurname);
        email=(EditText)findViewById(R.id.editContactEmail);
        number=(EditText)findViewById(R.id.editContactNumber);

        btnDiscard=(Button)findViewById(R.id.editContactDiscard);
        btnDiscard.setOnClickListener(this);

        btnSave=(Button)findViewById(R.id.editContactAccept);
        btnSave.setOnClickListener(this);



        genderGroup=(RadioGroup)findViewById(R.id.editContactGender);
        avatarGroup=(RadioGroup)findViewById(R.id.editAvatarGroup);


        contact=db.getContact(contactId);

        name.setText(contact.getImie());
        surname.setText(contact.getNazwisko());
        email.setText(contact.getEmail());
        number.setText(contact.getNumerTelefonu());


        if(contact.getPlec().equals("Mezczyzna")){
            genderButtonMale=(RadioButton)findViewById(R.id.editContactMale);
            genderButtonMale.setChecked(true);}
        if(contact.getPlec().equals("Kobieta")){
            genderButtonFemale=(RadioButton)findViewById(R.id.editContactFemale);
            genderButtonFemale.setChecked(true);
        }



        if(contact.getAvatar().equals("Avatar1")){
            avatarImg =(RadioButton)findViewById(R.id.editAvatar1);
            avatarImg.setChecked(true);
        }
        if(contact.getAvatar().equals("Avatar2")){
            avatarImg =(RadioButton)findViewById(R.id.editAvatar2);
            avatarImg.setChecked(true);
        }
        if(contact.getAvatar().equals("Avatar3")){
            avatarImg =(RadioButton)findViewById(R.id.editAvatar3);
            avatarImg.setChecked(true);
        }
        if(contact.getAvatar().equals("Avatar4")){
            avatarImg =(RadioButton)findViewById(R.id.editAvatar4);
            avatarImg.setChecked(true);
        }


        //należy ustwić plec i avatara na odpowiednim miejscu, oraz należy zaprogramować przyciski

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.editContactAccept:

                if(name.getText().toString().equals("")||surname.getText().toString().equals("")||number.getText().toString().equals("")){
                    break;}


                contact.setImie(name.getText().toString());
                contact.setNazwisko(surname.getText().toString());
                contact.setEmail(email.getText().toString());
                contact.setNumerTelefonu(Integer.parseInt(number.getText().toString()));


                int selectedIdGender = genderGroup.getCheckedRadioButtonId();
                genderChosen = (RadioButton) findViewById(selectedIdGender);
                contact.setPlec(genderChosen.getText().toString());


                int selectedIdAvatar = avatarGroup.getCheckedRadioButtonId();
                avatarChosen = (RadioButton) findViewById(selectedIdAvatar);


                switch (selectedIdAvatar){
                    case 2131230861:
                        contact.setAvatar("Avatar1");
                        break;
                    case 2131230862:
                        contact.setAvatar("Avatar2");
                        break;
                    case 2131230863:
                        contact.setAvatar("Avatar3");
                        break;
                    case 2131230864:
                        contact.setAvatar("Avatar4");
                        break;
                }


                db.editContat(contact);

                Toast.makeText(this, "Zmiany zostały zapisane", Toast.LENGTH_LONG).show();

                Intent intentBackToKsiazka=new Intent(this, MainKsiazkaTelefoniczna.class);
                startActivity(intentBackToKsiazka);
                break;

            case R.id.editContactDiscard:
                Toast.makeText(this, "Nie zapisano zmian", Toast.LENGTH_LONG).show();

                Intent intentBackToKsiazka2=new Intent(this, MainKsiazkaTelefoniczna.class);
                startActivity(intentBackToKsiazka2);
                break;
        }
    }
}