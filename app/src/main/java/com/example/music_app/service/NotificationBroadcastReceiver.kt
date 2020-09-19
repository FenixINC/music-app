package com.example.music_app.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.music_app.presentation.constant.NotificationConstants

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context?.sendBroadcast(
            Intent(NotificationConstants.INTENT_TRACK).putExtra(
                NotificationConstants.ACTION_TRACK,
                intent?.action
            )
        )
    }
}