package com.example.alarmmanager.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.alarmmanager.service.CancelService

class CancelReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            intent?.let { it1 -> startCancelService(context, it1) }
        }
    }

    private fun startCancelService(context: Context, it1: Intent) {
        val intentService = Intent(context, CancelService::class.java)
        intentService.flags  = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startForegroundService(intentService)
    }
}