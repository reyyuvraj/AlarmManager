package com.example.alarmmanager.util

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

fun notification(context: Context, pendingIntent: PendingIntent, title: String) {

    val builder = NotificationCompat.Builder(context, "Alarm")
        .setContentTitle(title)
        .setContentText("Alarm")
        .setAutoCancel(true)
        .setDefaults(NotificationCompat.DEFAULT_ALL)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setContentIntent(pendingIntent)

    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(111,builder.build())
}