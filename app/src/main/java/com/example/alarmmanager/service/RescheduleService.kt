package com.example.alarmmanager.service

import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService

class RescheduleService: LifecycleService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        val intentService = Intent(applicationContext, RescheduleService::class.java)
        applicationContext.stopService(intentService)
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}