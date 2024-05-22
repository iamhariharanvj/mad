package com.mad.myapp;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;


public class Message {
    public static void sendMessage(Context context, String[] phoneNumbers, String message){
        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.SEND_SMS}, 1);
            Toast.makeText(context, "Enable Permissions and Try Again", Toast.LENGTH_SHORT).show();
        } else {
            SmsManager smsManager = SmsManager.getDefault();
            for (String phoneNumber : phoneNumbers) {
                smsManager.sendTextMessage(phoneNumber.trim(), null, message, null, null);
            }
            Toast.makeText(context, "Message Sent", Toast.LENGTH_SHORT).show();
        }
    }

    public static void scheduleSms(Context context, String[] phoneNumbers, String message, Calendar calendar) {
        try {
            Intent intent = new Intent(context, SmsSenderBroadcastReceiver.class);
            intent.putExtra("phoneNumbers", phoneNumbers);
            intent.putExtra("message", message);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);

            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            Toast.makeText(context, "Message Scheduled", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            Log.d("MESSAGE", e.getMessage());
            Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


}

