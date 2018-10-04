package com.example.leedonggyu.mobile_app_develop;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.leedonggyu.mobile_app_develop.Manage.MemoActivity;

public class Broadcast extends BroadcastReceiver {

    private String text;
    private String date;
    private Intent dialog_intent;

    private SharedPreferences alarm_pre;
    private SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        text = intent.getStringExtra("text");
        date = intent.getStringExtra("date");

        dialog_intent = new Intent(context, showMemoActivity.class);
        dialog_intent.putExtra("text",text);
        dialog_intent.putExtra("date",date);
/*
        NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, MemoActivity.class), PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.liquor).setTicker("HETT")
                .setContentTitle(date).setContentText(text)
                .setContentIntent(pendingIntent);

        notificationmanager.notify(1, builder.build());
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
        Log.i("broadcast","실행함");
*/
        alarm_pre = context.getSharedPreferences("alarm",Context.MODE_PRIVATE);
        Boolean setting = alarm_pre.getBoolean("alarmSetting",true);

        if(setting) {
            PendingIntent pending_intent = PendingIntent.getActivity(context, 0, dialog_intent, PendingIntent.FLAG_ONE_SHOT);
            try {
                pending_intent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }
    }
}
