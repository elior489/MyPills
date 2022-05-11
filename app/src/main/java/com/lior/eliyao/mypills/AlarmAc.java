package com.lior.eliyao.mypills;

import static java.util.Calendar.AM;
import static java.util.Calendar.PM;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Timer;
import java.util.TimerTask;

public class AlarmAc extends AppCompatActivity {



//    NotificationManagerCompat notificationManager ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        notificationManager = NotificationManagerCompat.from(this);
        setContentView(R.layout.activity_alarm);
        Button button =findViewById(R.id.button);
//        AlarmManager [] arr ={ null,null,null,null};
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent =new Intent(AlarmAc.this,ReminderBroad.class);
                    PendingIntent pendingIntent =PendingIntent.getBroadcast(AlarmAc.this,0,intent ,0);

                    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                    long timeAtClick =System.currentTimeMillis();
                    long tenSec = 1000* 20;
                    long oneMin =1000* 30;
                    alarmManager.set(alarmManager.RTC_WAKEUP,timeAtClick+tenSec,pendingIntent);
                    alarmManager.set(alarmManager.RTC_WAKEUP,timeAtClick+oneMin,PendingIntent.getBroadcast(AlarmAc.this,1,intent ,0));


                }
            });



    }

//    public void notif(View view) {
//        Notification notification = new NotificationCompat.Builder(this,app.CHANNEL_1_ID)
//                .setSmallIcon(R.drawable.ic_baseline_local_hospital_24)
//                .setContentTitle("take your pills ")
//                .setContentText("do not forget to take you meds")
//                .setCategory(NotificationCompat.CATEGORY_ALARM)
//                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .build();
//        notificationManager.notify(1,notification);

//    }

}


