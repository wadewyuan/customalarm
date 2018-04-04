package com.wadey.customalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("[WY DEBUG]", "We're here and received !")

        // create indent for RingtoneService
        val serviceIntent = Intent(context, RingtoneService::class.java)

        // start service
        // null-safe in Kotlin with question mark
        context?.startService(serviceIntent)
    }
}