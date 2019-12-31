package com.ams.vocabforall;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Ams on 5/1/2017.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notificationIntent = new Intent(context, MainActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        Intent intentEmailView = new Intent (context, RandomTest.class);
        stackBuilder.addNextIntent(intentEmailView);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

      final NotificationCompat.Builder builder = new NotificationCompat.Builder(context);

               Notification notification = builder
//        final Notification.Builder builder = new Notification.Builder(this);
                .setContentTitle("Learn GRE WordList")
                .setContentText("Learn a word in a Jiffy")
                .setTicker("Vocabulary")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Do you know this GRE word ?")
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .setVibrate(new long[]{1000, 1000})
                .setContentIntent(pendingIntent).build();



        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);




    }

}
