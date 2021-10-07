package com.example.alarmmanager.receiver

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.util.Log
import com.example.alarmmanager.R
import java.util.*
import android.content.pm.PackageManager




private const val TAG = "AlarmReceiver"

class AlarmReceiver : BroadcastReceiver() {

    private lateinit var context: Context
    private lateinit var alarmManager: AlarmManager

    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.d(TAG, "onReceive: " + Date().toString())


        val pm = p0?.packageManager
        val launchIntent = pm?.getLaunchIntentForPackage("com.example.alarmmanager")
        launchIntent!!.putExtra("some_data", "value")
        p0.startActivity(launchIntent)

        //notification
//        val channelID = 1
//        val builder  = NotificationCompat.Builder(p0, channelID)

        //alarm tone
        val mediaPlayer = MediaPlayer.create(p0, R.raw.alarm_tone)
        mediaPlayer.isLooping = true
        mediaPlayer.start()
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