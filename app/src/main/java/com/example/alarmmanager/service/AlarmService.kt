package com.example.alarmmanager.service

import android.app.Service
import android.content.Intent
import android.media.Ringtone
import android.os.IBinder
import java.security.Provider

class AlarmService: Service() {

    private lateinit var ringtone: Ringtone
    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}