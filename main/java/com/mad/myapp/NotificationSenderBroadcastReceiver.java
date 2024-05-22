package com.mad.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationSenderBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String title = intent.getStringExtra("title");
        String text = intent.getStringExtra("text");
        Class<?> activity = (Class<?>) intent.getSerializableExtra("activity");

        Notification.showNotification(context,activity, title, text);
    }
}