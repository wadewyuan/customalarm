package com.wadey.customalarm

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log

class RingtoneService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        // do nothing
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("[WY DEBUG]", "start command in the service")

        // get the signal from intent extra value
        val alarmOn = intent?.getBooleanExtra(ALARM_ON, false)
        Log.d("[WY DEBUG]", "Alarm on state is $alarmOn")

        if (::mediaPlayer.isInitialized && mediaPlayer.isPlaying) {
            if (alarmOn!!) {
                Log.d("[WY DEBUG]", "Music playing and Alarm On button clicked, nothing will be done")
            } else {
                Log.d("[WY DEBUG]", "Music playing and Alarm Off button clicked, music will stop")
                mediaPlayer.stop()
            }
        } else {
            if (alarmOn!!) {
                Log.d("[WY DEBUG]", "Music not playing and Alarm On button clicked, music will start")
                mediaPlayer = MediaPlayer.create(this, R.raw.tone1)
                mediaPlayer.start()
            } else {
                Log.d("[WY DEBUG]", "Music not playing and Alarm Off button clicked, nothing will be done")
            }
        }

        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Log.d("[WY DEBUG]", "RingtoneService is destroyed")
    }
}
