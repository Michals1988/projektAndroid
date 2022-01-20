package com.example.projektandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DataBaseRemind extends SQLiteOpenHelper {

    public DataBaseRemind(Context context) {
        super(context, "reminders.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Reminders( id integer primary key autoincrement, title text, note text, time long)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addRemind(Remind remind) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("title", remind.getRemindTitle());
        wartosci.put("note", remind.getRemindNote());
        wartosci.put("time", remind.getRemindTime());
        db.insertOrThrow("Reminders", null, wartosci);
    }

    public void deleteRemind(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String[] arg = {"" + id};
        db.delete("Reminders", "id=?", arg);
    }

    public ArrayList<Remind> showUpcomingRemind() {
        ArrayList<Remind> reminders = new ArrayList<Remind>();
        String[] columns = {"id", "title", "note", "time"};
        SQLiteDatabase db = getReadableDatabase();
        long currentTime = System.currentTimeMillis();
        Cursor cursor = db.rawQuery("select id, title, note, time from Reminders where time > " + currentTime, null);
        while (cursor.moveToNext()) {
            Remind remind = new Remind();
            remind.setId(cursor.getInt(0));
            remind.setRemindTitle(cursor.getString(1));
            remind.setRemindNote(cursor.getString(2));
            remind.setRemindTime(cursor.getLong(3));
            reminders.add(remind);
        }
        return reminders;
    }

    public ArrayList<Remind> showOldRemind() {
        ArrayList<Remind> reminders = new ArrayList<Remind>();
        String[] columns = {"id", "title", "note", "time"};
        SQLiteDatabase db = getReadableDatabase();
        long currentTime = System.currentTimeMillis();
        Cursor cursor = db.rawQuery("select id, title, note, time from Reminders where time < " + currentTime, null);
        while (cursor.moveToNext()) {
            Remind remind = new Remind();
            remind.setId(cursor.getInt(0));
            remind.setRemindTitle(cursor.getString(1));
            remind.setRemindNote(cursor.getString(2));
            remind.setRemindTime(cursor.getLong(3));
            reminders.add(remind);
        }
        return reminders;
    }

    public Remind oneRemind(String title, String note, long timeSQL) {
        Remind remind = new Remind();
        String[] columns = {"id", "title", "note", "time"};
        SQLiteDatabase db = getReadableDatabase();
        long currentTime = System.currentTimeMillis();
        String titleSQL = "'" + title + "'";
        String noteSQL = "'" + note + "'";
        Cursor cursor = db.rawQuery("select id, title, note, time from Reminders where ( title = " + titleSQL + " and note = " + noteSQL + " and time = " + timeSQL + " )", null);
        if (cursor != null) {
            cursor.moveToFirst();
            remind.setId(cursor.getInt(0));
            remind.setRemindTitle(cursor.getString(1));
            remind.setRemindNote(cursor.getString(2));
            remind.setRemindTime(cursor.getLong(3));
        }
        return remind;

    }
}
