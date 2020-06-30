package com.example.music_app.core

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.music_app.R
import com.example.music_app.core.model.Action
import com.example.music_app.presentation.constant.Constants
import com.example.music_app.presentation.model.SongModel
import com.example.music_app.presentation.receiver.NotificationBroadcastReceiver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.URL
import javax.inject.Inject

class NotificationCore @Inject constructor(private var notification: Notification) {

    private lateinit var notificationManager: NotificationManager

    private var largeIcon: Bitmap? = null

    companion object {
        private const val PLAY = "Play"
        private const val NEXT = "Next"
        private const val PREVIOUS = "Previous"
        private const val NOTIFICATION_ID = 1

        private var TAG = NotificationCore::class.java.simpleName
    }

    fun createNotification(
        context: Context,
        songModel: SongModel,
        drawableActionButton: Int,
        actionPosition: Int,
        size: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            val mediaSessionCompat = MediaSessionCompat(context, TAG)

            loadIconFromFirebase(songModel.imageUrl)

            // Action Play
            val intentPlay = Intent(
                context,
                NotificationBroadcastReceiver::class.java
            ).setAction(Action.ACTION_PLAY.value)

            val pendingIntentPlay = PendingIntent.getBroadcast(
                context,
                0,
                intentPlay,
                PendingIntent.FLAG_UPDATE_CURRENT
            )

            // Action Previous
            val pendingIntentPrevious: PendingIntent?
            val actionPrevious: Int
            if (actionPosition == 0) {
                pendingIntentPrevious = null
                actionPrevious = 0
            } else {
                val intentPrevious = Intent(context, NotificationBroadcastReceiver::class.java)
                    .setAction(Action.ACTION_PREVIOUS.value)
                pendingIntentPrevious = PendingIntent.getBroadcast(
                    context,
                    0,
                    intentPrevious,
                    PendingIntent.FLAG_UPDATE_CURRENT
                )
                actionPrevious = R.drawable.ic_play_previous
            }

            // Action Next
            val pendingIntentNext: PendingIntent?
            val actionNext: Int
            if (actionPosition == size) {
                pendingIntentNext = null
                actionNext = 0
            } else {
                val intentNext = Intent(
                    context,
                    NotificationBroadcastReceiver::class.java
                ).setAction(Action.ACTION_NEXT.value)
                pendingIntentNext = PendingIntent.getBroadcast(
                    context, 0,
                    intentNext, PendingIntent.FLAG_UPDATE_CURRENT
                )
                actionNext = R.drawable.ic_play_next
            }

            notification = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_music_note)
                .setContentTitle(songModel.name)
                .setContentText(songModel.artist)
                .setLargeIcon(largeIcon)
                .setOnlyAlertOnce(true) //show notification for only first time
                .setShowWhen(false)
                .addAction(actionPrevious, PREVIOUS, pendingIntentPrevious)
                .addAction(drawableActionButton, PLAY, pendingIntentPlay)
                .addAction(actionNext, NEXT, pendingIntentNext)
                .setStyle(
                    androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2)
                        .setMediaSession(mediaSessionCompat.sessionToken) // Set notification collapsed
                )
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build()

            notificationManagerCompat.notify(NOTIFICATION_ID, notification)
        }
    }

    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.CHANNEL_ID,
                Constants.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            context.getSystemService(NotificationManager::class.java)?.let {
                notificationManager = it
                notificationManager.createNotificationChannel(channel)
            }
        }
    }

    fun cancelAllNotifications() {
        notificationManager.cancelAll()
    }

    private fun loadIconFromFirebase(imageUrl: String?) = runBlocking {
        withContext(Dispatchers.IO) {
            try {
                val url = URL(imageUrl)
                val input = url.openStream()
                largeIcon = BitmapFactory.decodeStream(input)
            } catch (e: IOException) {
                val result = e
            }
        }
    }
}