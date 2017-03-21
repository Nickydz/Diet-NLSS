package com.example.venussteve.notify_me;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.venussteve.notify_me.Repeating_activity;

/**
 * Created by Venus Steve on 10-03-2017.
 */

public class Notification_receiver_snack1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Bundle bundle = new Bundle();
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context, com.example.venussteve.notify_me.Repeating_activity_snack1.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);


          /*  NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent repeating_intent = new Intent(context,Repeating_activity.class);
            repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);*/

        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_up_float)
                .setContentTitle("Diet-NLSS")
                .setTicker("check")
                .setContentText("Snacks Time")
                .setAutoCancel(true);
        // builder.setContentIntent(pendingIntent);

        notificationManager.notify(100,builder.build());


    }

}
