package com.example.alarmmanager.service

import android.content.Intent
import android.os.IBinder
import androidx.lifecycle.LifecycleService

class CancelService: LifecycleService() {

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)

        val notificationService =
            Intent(applicationContext, AlarmService::class.java)
        applicationContext.stopService(notificationService)

        val cancelService =
            Intent(applicationContext, CancelService::class.java)
        applicationContext.stopService(cancelService)


        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        val intentService = Intent(applicationContext, CancelService::class.java)
        applicationContext.stopService(intentService)
    }

    override fun onBind(intent: Intent): IBinder? {
        super.onBind(intent)
        return null
    }
}