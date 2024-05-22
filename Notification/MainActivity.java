package com.mad.intent;

import android.app.Notification.Builder;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    public void showNotification(String channelId, int reqCode, String title, String text, PendingIntent intent){

        Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder = new Builder(getApplicationContext(), channelId)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle(title)
                    .setContentText(text)
                    .setContentIntent(intent)
                    .setAutoCancel(true);
        }

        NotificationManager notificationManager = getSystemService(NotificationManager.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel(channelId,"description", NotificationManager.IMPORTANCE_DEFAULT));
        }

        notificationManager.notify(reqCode, builder.build());

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notificationBtn = findViewById(R.id.notificationBtn);
        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CHANNEL_ID = "123";
                int REQ_CODE = 0;
                Intent i = new Intent(getApplicationContext(), NotificationActivity.class);
                PendingIntent intent = PendingIntent.getActivity(getApplicationContext(),REQ_CODE,i,PendingIntent.FLAG_IMMUTABLE);
                showNotification(CHANNEL_ID, REQ_CODE, "Title", "Content", intent);

            }
        });

    }
}