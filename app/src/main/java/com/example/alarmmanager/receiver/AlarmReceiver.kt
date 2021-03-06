package com.example.alarmmanager.receiver

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.alarmmanager.service.AlarmService
import java.util.*


class AlarmReceiver : BroadcastReceiver() {

    private lateinit var alarmManager: AlarmManager
    private var TAG = "AlarmReceiver"

    override fun onReceive(context: Context?, p1: Intent?) {

        Log.d(TAG, "onReceive: " + Date().toString())

        context?.let {
            p1?.let { startAlarmService(context, it) }
        }
    }

    private fun startAlarmService(
        context: Context,
        intent: Intent
    ) {
        val intentService = Intent(context, AlarmService::class.java)
        intentService.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startForegroundService(intentService)
    }
}