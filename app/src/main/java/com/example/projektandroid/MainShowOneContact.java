package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;

public class MainShowOneContact extends AppCompatActivity implements View.OnClickListener{

    DataBaseContacts db=new DataBaseContacts(this);

    TextView name, surname, email, gender, number;
    ImageView avatar;
    int contactId;
    Button btnEdit, btnDelete, btnCall;
    Contact contact=new Contact();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_show_one_contact);

        contactId = getIntent().getIntExtra("idContact",2);

        name=(TextView)findViewById(R.id.showOneContactName);
        surname=(TextView)findViewById(R.id.showOneContactSurname);
        email=(TextView)findViewById(R.id.showOneContactEmail);
        gender=(TextView)findViewById(R.id.showOneContactGender);
        number=(TextView)findViewById(R.id.showOneContactNumber);
        avatar=(ImageView)findViewById(R.id.showOneContactAvatar);

        btnEdit=(Button)findViewById(R.id.showOneContactEdit);
        btnDelete=(Button)findViewById(R.id.showOneContactDelete);
        btnCall=(Button)findViewById(R.id.showOneContactCall);

        btnEdit.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnCall.setOnClickListener(this);

        contact=new Contact();
        contact=db.getContact(contactId);

        name.setText("Imie: "+contact.getImie());
        surname.setText("Nazwisko: "+contact.getNazwisko());
        email.setText("E-mail: "+contact.getEmail());
        number.setText("Numer telefonu: "+contact.getNumerTelefonu());
        gender.setText("Płeć: "+contact.getPlec());


        if (contact.getAvatar().equals("Avatar1")) {
            avatar.setImageResource(R.drawable.man1128);
        }
        if (contact.getAvatar().equals("Avatar2")) {
            avatar.setImageResource(R.drawable.woman1128);
        }
        if (contact.getAvatar().equals("Avatar3")) {
            avatar.setImageResource(R.drawable.man2128);
        }
        if (contact.getAvatar().equals("Avatar4")) {
            avatar.setImageResource(R.drawable.woman2128);
        }



    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.showOneContactEdit:

                Intent intentEditContact = new Intent(v.getContext(), MainEditContact.class);

                intentEditContact.putExtra("idContact", contactId);

                v.getContext().startActivity(intentEditContact);

                break;

            case R.id.showOneContactDelete:

                db.deleteContact(contactId);

                Toast.makeText(this, "Kontakt został usuniety!", Toast.LENGTH_LONG).show();

                Intent intentBackToMainMenu=new Intent(this, MainActivity.class);
                startActivity(intentBackToMainMenu);
                break;

            case R.id.showOneContactCall:
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                String number="tel:"+contact.getNumerTelefonu();
                phoneIntent.setData(Uri.parse(number));

                if (ActivityCompat.checkSelfPermission(MainShowOneContact.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                startActivity(phoneIntent);
    }
}
}