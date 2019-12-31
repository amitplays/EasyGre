package com.ams.vocabforall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by Ams on 11/21/2016.
 */

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

// http://www.singhajit.com/schedule-local-notification-in-android/
       startService(new Intent(SplashScreen.this, AlarmService.class));


//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
//        notificationIntent.addCategory("android.intent.category.DEFAULT");
//        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.SECOND, 5);
//
//        Toast.makeText(this,""+cal.getTimeInMillis(),Toast.LENGTH_SHORT).show();
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
//




        Thread timerThread = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                    Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.nochange, R.anim.slide_in);
                    finish();
                }

            }

        };
        timerThread.start();


    }


}
