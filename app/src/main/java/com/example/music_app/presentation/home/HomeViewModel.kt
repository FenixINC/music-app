package com.example.music_app.presentation.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.music_app.presentation.base.BaseViewModel
import com.example.music_app.presentation.model.GenreModel
import com.example.music_app.presentation.model.ListItem
import com.example.music_app.presentation.model.ProgressItem
import com.example.music_app.presentation.model.SongModel
import com.example.network.repository.MusicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: MusicRepository
) : BaseViewModel() {

    companion object {
        private const val POSITION_DEFAULT = 0
    }

    private val _songData = MutableLiveData<List<ListItem>>()
    val songData: LiveData<List<ListItem>> = _songData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _songData.postValue(getLoaders())
            _songData.postValue(getGenreList())
        }
    }

    private fun getLoaders() = listOf(
        GenreModel(
            genre = "Genre 1",
            songList = LongRange(1, 10).map { ProgressItem }
        ),
        GenreModel(
            genre = "Genre 2",
            songList = LongRange(1, 10).map { ProgressItem }
        ),
        GenreModel(
            genre = "Genre 3",
            songList = LongRange(1, 10).map { ProgressItem }
        )
    )

    private suspend fun getGenreList(): List<ListItem> {
        delay(2_000L)
        return listOf(
            GenreModel(
                genre = "Genre 1",
                songList = LongRange(1, 10).map {
                    SongModel(
                        id = it,
                        name = "Title $it",
                        artist = "Artist $it"
                    )
                }
            ),
            GenreModel(
                genre = "Genre 2",
                songList = LongRange(1, 10).map {
                    SongModel(
                        id = it,
                        name = "Title $it",
                        artist = "Artist $it"
                    )
                }
            ),
            GenreModel(
                genre = "Genre 3",
                songList = LongRange(1, 10).map {
                    SongModel(
                        id = it,
                        name = "Title $it",
                        artist = "Artist $it"
                    )
                }
            )
        )
    }

//    val songListLiveData = MutableLiveData<List<GenreModel>>()
//    val songLiveData = MutableLiveData<SongModel>()
//    val isPlayLiveData = MutableLiveData<Boolean>()
//    val actionLiveData = MutableLiveData<String>()
//    val actionPositionLiveData = MutableLiveData<Int>()
//
//    var positionAction = POSITION_DEFAULT
//
//    init {
//        actionPositionLiveData.value = POSITION_DEFAULT
//    }
//
//    fun setSong(songModel: SongModel) {
//        songLiveData.value = songModel
//    }
//
//    fun setIsPlay(isPlay: Boolean) {
//        isPlayLiveData.value = isPlay
//    }
//
//    fun setActionPosition(position: Int) {
//        actionPositionLiveData.value = position
//    }
//
//    fun loadSongListFirebase() = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            loadingLiveData.postValue(true)
//            homeRepository.loadSongListFirebase().collect { data ->
////                songListLiveData.postValue(mapResponseToModel(data))
//            }
//        } catch (e: Exception) {
//            errorMessageLiveData.postValue(e.message)
//        } finally {
//            loadingLiveData.postValue(false)
//        }
//    }
//
//    fun retroMusicChangesSubscription() = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            loadingLiveData.postValue(true)
//            homeRepository.retroMusicChangesSubscription().collect { data ->
////                songListLiveData.postValue(mapResponseToModel(data))
//            }
//        } catch (e: Exception) {
//            errorMessageLiveData.postValue(e.message)
//        } finally {
//            loadingLiveData.postValue(false)
//        }
//    }
//
//    //TODO: move to BroadcastReceiverCore
//    val broadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            actionLiveData.value = intent?.extras?.getString(NotificationConstants.ACTION_TRACK)
//        }
//    }
//
//    val completionListener = MediaPlayer.OnCompletionListener {
////        setIsPlay(false)
////        actionLiveData.value = Action.ACTION_PLAY.value
//    }
//
//    @ExperimentalCoroutinesApi
//    fun loadSongMutableFlow() = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            homeRepository.songFlowData.collect { data ->
//
//            }
//        } catch (e: Exception) {
//
//        } finally {
//
//        }
//    }
}