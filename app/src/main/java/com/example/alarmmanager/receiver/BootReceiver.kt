package com.example.alarmmanager.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.example.alarmmanager.service.AlarmService

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Boot", "onReceive: Booted")
        context?.let {
            if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
                Log.d("Boot", "onReceive: Boot intent")
                val toastText = String.format("Alarm Reboot")
                Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
                startRescheduleAlarmsService(context)
            }
        }
    }

    private fun startRescheduleAlarmsService(context: Context) {
        val intentService =
            Intent(context, AlarmService::class.java)//make a rescheduled_alarm service
        context.startForegroundService(intentService)
    }
}