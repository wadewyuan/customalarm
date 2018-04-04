package com.wadey.customalarm

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class RingtoneService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        // do nothing
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("[WY DEBUG]", "start command in the service")
        mediaPlayer = MediaPlayer.create(this, R.raw.tone1)
        mediaPlayer.start()
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        Toast.makeText(this, "RingtoneService destroyed", Toast.LENGTH_SHORT).show()
    }
}
