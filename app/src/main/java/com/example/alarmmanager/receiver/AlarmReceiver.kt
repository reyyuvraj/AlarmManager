package com.example.alarmmanager.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import com.example.alarmmanager.R
import com.example.alarmmanager.service.AlarmService
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    private lateinit var alarmManager: AlarmManager
    private var TAG = "AlarmReceiver"

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(TAG, "onReceive: " + Date().toString())

        //alarm tone
        val mediaPlayer = MediaPlayer.create(p0, R.raw.alarm_tone)
        mediaPlayer.isLooping = true
        mediaPlayer.start()

        /*p0?.let {
            p1?.let { startAlarmService(p0, it) }
        }*/

    }

    private fun startAlarmService(
        context: Context,
        intent: Intent
    ) {
        val intent = Intent(context, AlarmService::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun startAlarm(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val seconds = 5000
        Log.d(TAG, "startAlarm: $seconds")
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        Log.d(TAG, "startAlarm: " + Date().toString())
        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + seconds,
            pendingIntent
        )
    }
}