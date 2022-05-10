package com.lior.eliyao.mypills;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderBroad  extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Notification notification = new NotificationCompat.Builder(context,app.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_local_hospital_24)
                .setContentTitle("take your pills ")
                .setContentText("do not forget to take you meds")
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        NotificationManagerCompat notificationManager =NotificationManagerCompat.from(context);
        try {
            notificationManager.notify(200, notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
