package com.example.projektandroid;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ReminderService extends Service {
    public ReminderService() {
    }

    public int onStartCommand(Intent intent, int flags, int id){
        String  remindName=intent.getStringExtra("");
        long    remindTime=intent.getLongExtra("",0);
        long    currentTime=System.currentTimeMillis();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
