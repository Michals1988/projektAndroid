package com.example.projektandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainAddReminder extends AppCompatActivity implements View.OnClickListener {

    TextView dateOfRemind, timeOfRemind;
    EditText remindTitle, remindNote;
    Calendar mCalender = Calendar.getInstance();
    Button btnSave, btnDiscard;
    Date date;

    DataBaseRemind db = new DataBaseRemind(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_reminder);

        remindTitle = (EditText) findViewById(R.id.addReminderTitle);
        remindNote = (EditText) findViewById(R.id.addReminderNote);

        dateOfRemind = (TextView) findViewById(R.id.addEditTextDateRemind);
        dateOfRemind.setOnClickListener(this);

        timeOfRemind = (TextView) findViewById(R.id.addEditTextTimeRemind);
        timeOfRemind.setOnClickListener(this);

        btnSave = (Button) findViewById(R.id.addReminderSave);
        btnSave.setOnClickListener(this);

        btnDiscard = (Button) findViewById(R.id.addReminderDiscard);
        btnDiscard.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addEditTextDateRemind:

                int year = mCalender.get(Calendar.YEAR);
                int month = mCalender.get(Calendar.MONTH);
                int day = mCalender.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dp = new DatePickerDialog(this, android.R.style.Theme_Holo_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        dateOfRemind.setText(checkDigit(dayOfMonth) + "-" + checkDigit((monthOfYear + 1)) + "-" + year);
                    }
                }, year, month, day);
                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
                break;

            case R.id.addEditTextTimeRemind:
                int hour = mCalender.get(Calendar.HOUR_OF_DAY);
                int min = mCalender.get(Calendar.MINUTE);

                TimePickerDialog Tp = new TimePickerDialog(this, android.R.style.Theme_Material_Dialog, new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        timeOfRemind.setText(checkDigit(hourOfDay) + ":" + checkDigit(minute));

                    }
                }, hour, min, true);
                Tp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Tp.show();
                break;

            case R.id.addReminderSave:

                if (remindNote.getText().toString().equals("") || remindTitle.getText().toString().equals("") || timeOfRemind.getText().toString().equals("Ustaw godzine") || dateOfRemind.getText().toString().equals("Ustaw date")) {
                    break;
                }
                createNotificationChannel();

                String timeString = timeOfRemind.getText().toString();

                String dateString = dateOfRemind.getText().toString();
                String dateTimeString = dateString + " " + timeString;

                DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                try {
                    date = formatter.parse(dateTimeString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Remind remind = new Remind();

                remind.setRemindTitle(remindTitle.getText().toString());
                remind.setRemindNote(remindNote.getText().toString());
                remind.setRemindTime(date.getTime());
                db.addRemind(remind);
                Remind remindSetNewIdFromSQL=new Remind();
                remindSetNewIdFromSQL=db.oneRemind(remind.getRemindTitle(), remind.getRemindNote(), remind.getRemindTime());


                Toast.makeText(this, "Zapisano przypomnienie!", Toast.LENGTH_LONG).show();

                Intent intentNotification= new Intent(this, ReminderBroadcast.class);

                intentNotification.putExtra("titleReminder", remindTitle.getText().toString());
                intentNotification.putExtra("noteReminder", remindNote.getText().toString());
                intentNotification.putExtra("idReminder", remindSetNewIdFromSQL.getId());


                PendingIntent pendingIntent=PendingIntent.getBroadcast(this, 0, intentNotification, 0);

                AlarmManager alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);

                alarmManager.set(alarmManager.RTC_WAKEUP,date.getTime(), pendingIntent);


                Intent intentRemindMenu = new Intent(this, MainPrzypominajka.class);
                startActivity(intentRemindMenu);


                break;

            case R.id.addReminderDiscard:

                Toast.makeText(this, "Nie zapisano przypomnienia", Toast.LENGTH_LONG).show();
                Intent intentRemindMenu2 = new Intent(this, MainPrzypominajka.class);
                startActivity(intentRemindMenu2);
                break;
        }
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence name = remindTitle.getText().toString();
            String description = remindNote.getText().toString();
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notify", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager=getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
