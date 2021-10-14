package com.example.alarmmanager.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.media.Ringtone
import android.os.IBinder
import com.example.alarmmanager.R
import java.security.Provider

class AlarmService: Service() {

    private lateinit var ringtone: Ringtone
    override fun onBind(intent: Intent?): IBinder? = null

    override fun onCreate() {
        super.onCreate()

        //alarm tone
        val mediaPlayer = MediaPlayer.create(this, R.raw.alarm_tone)
        mediaPlayer.isLooping = true
        mediaPlayer.start()


    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}