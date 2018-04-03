package com.wadey.customalarm

import android.app.AlarmManager
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var timePicker : TimePicker
    lateinit var alarmTextView : TextView
    lateinit var alarmOnButton : Button
    lateinit var alarmOffButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get alarm manager
        var alarmManager : AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        // initialize the components
        timePicker  = findViewById(R.id.timePicker)
        alarmTextView  = findViewById(R.id.alarmText)
        alarmOnButton  = findViewById(R.id.alarmOn)
        alarmOffButton  = findViewById(R.id.alarmOff)

        alarmOnButton.setOnClickListener({ setAlarmText("Alarm On")})
        alarmOffButton.setOnClickListener { setAlarmText("Alarm Off") }

        // get calendar instance
        var calendar = Calendar.getInstance()
    }

    private fun setAlarmText(s: String) {
        alarmTextView.text = s
    }
}
