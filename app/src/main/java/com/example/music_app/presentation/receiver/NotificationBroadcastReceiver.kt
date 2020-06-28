package com.example.music_app.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.music_app.presentation.constant.Constants

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.sendBroadcast(
            Intent(Constants.INTENT_TRACK).putExtra(
                Constants.ACTION_TRACK,
                intent?.action
            )
        )
    }
}