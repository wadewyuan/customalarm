package com.wadey.busalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("[WY DEBUG]", "We're here and received !")

        // get the signal from intent extra value
        val alarmOn = intent?.getBooleanExtra(ALARM_ON, false)
        // create intent for RingtoneService
        val serviceIntent = Intent(context, RingtoneService::class.java)
        serviceIntent.putExtra(ALARM_ON, alarmOn)

        // start service
        // null-safe in Kotlin with question mark
        context?.startService(serviceIntent)
    }
}