package com.example.music_app.core

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer

class MediaPlayerCore {

    private var mediaPlayer: MediaPlayer? = null

    fun createMediaPlayer(context: Context, songUrl: String) {
        mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(songUrl)
            prepare()
            start()
        }
    }

    fun pauseMediaPlayer() {
        mediaPlayer?.pause()
    }

    fun stopMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }
}