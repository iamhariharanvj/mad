package com.mad.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class Whatsapp {
    public static void sendGroupMessage(Context context, String groupLink, String message) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        sendIntent.setPackage("com.whatsapp");

        sendIntent.putExtra("jid", groupLink);

        try {
            context.startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }

    public static void sendMessage(Context context, String phoneNumber, String message) {
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);

        String uri = "https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message);
        sendIntent.setData(Uri.parse(uri));
        sendIntent.setPackage("com.whatsapp");

        try {
            context.startActivity(sendIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "WhatsApp not installed.", Toast.LENGTH_SHORT).show();
        }
    }

}
