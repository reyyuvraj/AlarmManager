package com.example.alarmmanager.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.alarmmanager.service.SnoozeService

class SnoozeReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { snoozeAlarmService(context, intent) }
        }
    }

    private fun snoozeAlarmService(context: Context, intent: Intent) {
        val intent = Intent(context, SnoozeService::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startForegroundService(intent)
    }
}