package com.example.projektandroid;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.view.View;

import java.io.File;

/**
 * Implementation of App Widget functionality.
 */




public class NoteWidget extends AppWidgetProvider {

    TextView widgetTextName, widgetTextNote;
    Button button;


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {












    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);



            String folder = context.getFilesDir().getAbsolutePath() + File.separator + "notes";

            File subFolder = new File(folder);

            String[] values = subFolder.list();
            int i=0;
            String noteName=values[i].replace(".txt", "");
            CharSequence widgetText = noteName;
            // Construct the RemoteViews object


            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.note_widget);

            views.setTextViewText(R.id.widgetTextNote, widgetText);
            views.setTextViewText(R.id.widgetTextName, widgetText);

            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }


    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created


    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

