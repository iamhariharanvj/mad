package com.mad.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;



public class SmsSenderBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String[] phoneNumbers = intent.getStringArrayExtra("phoneNumbers");
        String message = intent.getStringExtra("message");

        if (phoneNumbers != null && message != null) {
            SmsManager smsManager = SmsManager.getDefault();
            for (String phoneNumber : phoneNumbers) {
                smsManager.sendTextMessage(phoneNumber.trim(), null, message, null, null);
            }
            Toast.makeText(context, "Message Sent", Toast.LENGTH_SHORT).show();
        }
    }
}

