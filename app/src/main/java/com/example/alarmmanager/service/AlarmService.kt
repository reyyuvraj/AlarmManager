package com.example.alarmmanager.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.alarmmanager.R
import com.example.alarmmanager.receiver.AlarmReceiver

class AlarmService : Service() {

    private lateinit var ringtone: Ringtone
    private lateinit var uri: Uri
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        uri =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

        ringtone =RingtoneManager.getRingtone(applicationContext, uri)
        //alarm tone

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val channelId = "basic"
        val channelName = "Alarm"

        val notificationIntent =
            Intent(this, AlarmReceiver::class.java)//a different class is supposed to be used here


        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notification =
            NotificationCompat.Builder(this, channelId)
                .setContentTitle("Alarm running")
                .setContentText("Alarm for time")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build()

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

        ringtone.play()

        /*val mediaPlayer = MediaPlayer.create(this, R.raw.alarm_tone)
        mediaPlayer.isLooping = true
        mediaPlayer.start()*/

        startForeground(2, notification)

        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        ringtone.stop()
    }
}