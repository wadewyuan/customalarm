package com.wadey.busalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

const val ALARM_ON = "alarm_on"

class MainActivity : AppCompatActivity() {

    private lateinit var timePicker: TimePicker
    private lateinit var alarmTextView: TextView
    private lateinit var alarmOnButton: Button
    private lateinit var alarmOffButton: Button
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get alarm manager
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // initialize the components
        timePicker = findViewById(R.id.timePicker)
        alarmTextView = findViewById(R.id.alarmText)
        alarmOnButton = findViewById(R.id.alarmOn)
        alarmOffButton = findViewById(R.id.alarmOff)

        // get calendar instance
        val calendar = Calendar.getInstance()
        val intent = Intent(this, AlarmReceiver::class.java)

        alarmOnButton.setOnClickListener {
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
            calendar.set(Calendar.MINUTE, timePicker.minute)
            // String template & Trinocular operation in Kotlin
            val hourString = "${timePicker.hour}"
            val minuteString = if (timePicker.minute >= 10) "${timePicker.minute}" else "0${timePicker.minute}"

            Log.d("[WY DEBUG]", "$hourString:$minuteString")
            setAlarmText("Alarm On $hourString:$minuteString")

            // put extra in intent to tell receiver that it's an "ON" signal
            intent.putExtra(ALARM_ON, true)
            // create a pending intent that delays the intent until specified calendar time
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            // set alarm manager to wake up after calendar time
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        }
        alarmOffButton.setOnClickListener {
            setAlarmText("Alarm Off")
            alarmManager.cancel(pendingIntent)
            // put extra in intent to tell receiver that it's an "OFF" signal
            intent.putExtra(ALARM_ON, false)
            // broadcast the intent
            sendBroadcast(intent)
            Log.d("[WY DEBUG]", "Alarm canceled.")
        }
    }

    private fun setAlarmText(s: String) {
        alarmTextView.text = s
    }
}
