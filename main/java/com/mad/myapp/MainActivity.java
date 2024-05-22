package com.mad.myapp;

import android.app.NotificationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NotificationManager manager = getSystemService(NotificationManager.class);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        Notification.scheduleNotification(this, "Title", "Text",MainActivity.class,calendar);
    }

}