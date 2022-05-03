package com.lior.eliyao.mypills;

import static java.util.Calendar.AM;
import static java.util.Calendar.PM;

import android.app.Notification;
import android.app.NotificationManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextClock;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmAc extends AppCompatActivity {
 private NotificationManagerCompat notificationManager ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         notificationManager= NotificationManagerCompat.from(this);
        setContentView(R.layout.activity_alarm);
    }
    public void Sendnotif( View v){

    }

    public void notif(View view) {
        Notification notification = new NotificationCompat.Builder(this,app.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_local_hospital_24)
                .setContentTitle("take your pills ")
                .setContentText("do not forget to take you meds")
                .setCategory(NotificationCompat.CATEGORY_ALARM)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        notificationManager.notify(1,notification);

    }
}


