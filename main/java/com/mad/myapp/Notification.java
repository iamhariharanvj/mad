package com.mad.myapp;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import java.util.Calendar;


public class Notification {

    public static void showNotification(Context context, Class activity, String title, String text){

        String channelId = "NOTIFICATION_CHANNEL";
        NotificationCompat.Builder builder = null;
        Intent i = new Intent(context, activity);
        PendingIntent intent = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_IMMUTABLE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new NotificationCompat.Builder(context, channelId)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setContentIntent(intent)
                    .setAutoCancel(true);
        }

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,"description", NotificationManager.IMPORTANCE_DEFAULT));
        }

        notificationManager.notify(0, builder.build());
    }

    public static void scheduleNotification(Context context, String title, String text, Class<?> activity, Calendar calendar) {
        try {
            Intent intent = new Intent(context, NotificationSenderBroadcastReceiver.class);
            intent.putExtra("title", title);
            intent.putExtra("text", text);
            intent.putExtra("activity", activity);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            Toast.makeText(context, "Notification Scheduled", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.d("MESSAGE", e.getMessage());
        }
    }


}
