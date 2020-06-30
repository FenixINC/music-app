package com.example.music_app.core

import android.media.AudioManager
import android.media.MediaPlayer

class MediaPlayerCore {

    private var mediaPlayer: MediaPlayer? = null

    fun createMediaPlayer(songUrl: String, listener: MediaPlayer.OnCompletionListener) {
        mediaPlayer = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(songUrl)
            prepare()
            setOnCompletionListener(listener)
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