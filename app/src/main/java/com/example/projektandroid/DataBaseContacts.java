package com.example.projektandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DataBaseContacts extends SQLiteOpenHelper {

    public DataBaseContacts(Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Contacts( id integer primary key autoincrement, imie text, nazwisko text, plec text, numerTelefonu int, email text,avatar text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("imie", contact.getImie());
        wartosci.put("nazwisko", contact.getNazwisko());
        wartosci.put("plec", contact.getPlec());
        wartosci.put("numerTelefonu", contact.getNumerTelefonu());
        wartosci.put("email", contact.getEmail());
        wartosci.put("avatar", contact.getAvatar());
        db.insertOrThrow("Contacts", null, wartosci);
    }

    public ArrayList<Contact> showAllContacts() {
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        String[] columns={"id", "imie", "nazwisko", "plec", "numerTelefonu", "email", "avatar"};
        SQLiteDatabase db= getReadableDatabase();
        Cursor cursor=db.query("Contacts", columns, null, null, null, null, "nazwisko");
        while(cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setImie(cursor.getString(1));
            contact.setNazwisko(cursor.getString(2));
            contact.setPlec(cursor.getString(3));
            contact.setNumerTelefonu(cursor.getInt(4));
            contact.setEmail(cursor.getString(5));
            contact.setAvatar(cursor.getString(6));
            contacts.add(contact);
        }
        return contacts;
    }

    public void deleteContact(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] arg = {"" + id};
        db.delete("Contacts", "id=?", arg);
    }

    public void editContat(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("imie", contact.getImie());
        values.put("nazwisko", contact.getNazwisko());
        values.put("plec", contact.getPlec());
        values.put("numerTelefonu", contact.getNumerTelefonu());
        values.put("email", contact.getEmail());
        values.put("avatar", contact.getAvatar());
        String arg[] = {contact.getId() + ""};
        db.update("Contacts", values, "id=?", arg);
    }

    public Contact getContact(long id) {
        Contact contact = new Contact();
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"id", "imie", "nazwisko", "plec", "numerTelefonu", "email", "avatar"};
        String arg[] = {id + ""};
        Cursor cursor = db.query("Contacts", columns, "id=?", arg, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            contact.setId(cursor.getInt(0));
            contact.setImie(cursor.getString(1));
            contact.setNazwisko(cursor.getString(2));
            contact.setPlec(cursor.getString(3));
            contact.setNumerTelefonu(cursor.getInt(4));
            contact.setEmail(cursor.getString(5));
            contact.setAvatar(cursor.getString(6));
        }
        return contact;
    }

    public ArrayList<Contact> searchContacts(String search) {
        ArrayList<Contact> contacts=new ArrayList<Contact>();
        String[] columns={"id", "imie", "nazwisko", "plec", "numerTelefonu", "email", "avatar"};
        SQLiteDatabase db= getReadableDatabase();
        String arg ="'%"+search+"%'";
        Cursor cursor=db.rawQuery("select id, imie, nazwisko, plec, numerTelefonu, email, avatar from Contacts where ( imie like "+arg+" or nazwisko like "+arg+" or  numerTelefonu like "+arg+" ) order by nazwisko",null);
        //Cursor cursor=db.query("Contacts", columns, "imie=?, nazwisko=?, numerTelefonu=?", null, null, null, "nazwisko");
        while(cursor.moveToNext()){
            Contact contact = new Contact();
            contact.setId(cursor.getInt(0));
            contact.setImie(cursor.getString(1));
            contact.setNazwisko(cursor.getString(2));
            contact.setPlec(cursor.getString(3));
            contact.setNumerTelefonu(cursor.getInt(4));
            contact.setEmail(cursor.getString(5));
            contact.setAvatar(cursor.getString(6));
            contacts.add(contact);
        }
        return contacts;
    }


}
