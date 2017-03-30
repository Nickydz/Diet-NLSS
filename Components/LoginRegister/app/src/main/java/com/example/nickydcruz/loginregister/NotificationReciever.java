package com.example.nickydcruz.loginregister;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
/**
 * Created by Nicky D. Cruz on 3/29/2017.
 */
public class NotificationReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context,Notification.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int uniqueid = intent.getIntExtra("id",100);
//        //if we want ring on notifcation then uncomment below line//
//        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Uri alarmSound = Uri.parse("android.resource://"+context.getPackageName()+"/"+R.raw.sherlocker_xmeo4wao);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context).
                setSmallIcon(R.mipmap.nlss_crop).
                setContentIntent(pendingIntent).
                setContentText("this is my notification").
                setContentTitle("my notificaton").
                setSound(alarmSound).
        setAutoCancel(true);
        notificationManager.notify(uniqueid,builder.build());

    }
}
