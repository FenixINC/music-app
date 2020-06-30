package com.example.music_app.presentation.viewmodel

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.example.music_app.data.repository.HomeRepository
import com.example.music_app.presentation.constant.Constants
import com.example.music_app.presentation.mapper.mapResponseToModel
import com.example.music_app.presentation.model.SongModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository
) : BaseViewModel() {

    companion object {
        private const val POSITION_DEFAULT = 0
    }

    val songListLiveData = MutableLiveData<List<SongModel>>()
    val songLiveData = MutableLiveData<SongModel>()
    val isPlayLiveData = MutableLiveData<Boolean>()
    val actionLiveData = MutableLiveData<String>()
    val actionPositionLiveData = MutableLiveData<Int>()

    var positionAction = POSITION_DEFAULT

    init {
        actionPositionLiveData.value = POSITION_DEFAULT
    }

    fun setSong(songModel: SongModel) {
        songLiveData.value = songModel
    }

    fun setIsPlay(isPlay: Boolean) {
        isPlayLiveData.value = isPlay
    }

    fun setActionPosition(position: Int) {
        actionPositionLiveData.value = position
    }

    fun loadSongListFirebase() = CoroutineScope(Dispatchers.IO).launch {
        try {
            loadingLiveData.postValue(true)
            homeRepository.loadSongListFirebase().collect { data ->
                songListLiveData.postValue(mapResponseToModel(data))
            }
        } catch (e: Exception) {
            errorMessageLiveData.postValue(e.message)
        } finally {
            loadingLiveData.postValue(false)
        }
    }

    //TODO: move to BroadcastReceiverCore
    val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            actionLiveData.value = intent?.extras?.getString(Constants.ACTION_TRACK)
        }
    }

    val completionListener = MediaPlayer.OnCompletionListener {
//        setIsPlay(false)
//        actionLiveData.value = Action.ACTION_PLAY.value
    }
}