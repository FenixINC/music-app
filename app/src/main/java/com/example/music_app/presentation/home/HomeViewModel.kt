package com.example.music_app.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.usecases.LoadGenreListUseCase
import com.example.music_app.presentation.base.BaseViewModel
import com.example.music_app.presentation.mapper.mapDomainToModel
import com.example.music_app.presentation.model.GenreModel
import com.example.music_app.presentation.model.ProgressItem
import com.example.music_app.presentation.viewintent.HomeViewIntent
import com.example.music_app.presentation.viewstate.HomeViewState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.koin.core.inject

class HomeViewModel : BaseViewModel() {

    private val loadGenreListUseCase by inject<LoadGenreListUseCase>()

    companion object {
        private const val POSITION_DEFAULT = 0
    }

//    private val _songData = MutableLiveData<List<ListItem>>()
//    val songData: LiveData<List<ListItem>> = _songData

    val intentChannel = Channel<HomeViewIntent>(Channel.UNLIMITED)

    private val _homeViewState = MutableLiveData<HomeViewState>()
    val homeViewState: LiveData<HomeViewState> = _homeViewState

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            _songData.postValue(getLoaders())
//        }
//    }

    fun setIntent(homeViewIntent: HomeViewIntent) {
        viewModelScope.launch {
            when (homeViewIntent) {
                is HomeViewIntent.FetchGenreList -> {
                    _homeViewState.postValue(HomeViewState.LoadingState(isLoading = true))
                    loadGenreList(homeViewIntent.fileName)
                }
            }
        }
    }

    private suspend fun loadGenreList(fileName: String) = loadGenreListUseCase.loadGenreList(
        fileName = fileName,
        onError = { errorMessage ->
            _homeViewState.postValue(HomeViewState.Error(errorMessage = errorMessage))
        },
        onIsLoading = { isLoading ->
            if (isLoading) {
                getLoaders()
            }
            _homeViewState.postValue(HomeViewState.LoadingState(isLoading = isLoading))
        },
        onSuccess = { genreList ->
            _homeViewState.postValue(HomeViewState.Genres(mapDomainToModel(genreList)))
        }
    )

//    private fun handleIntent() {
//        viewModelScope.launch {
//            intentChannel.consumeAsFlow().collect { homeIntent ->
//                when (homeIntent) {
//                    is HomeViewIntent.FetchGenreList -> {
//                        loadGenreList()
//                    }
//                }
//            }
//        }
//    }

    private fun getLoaders() = listOf(
        GenreModel(
            genreName = "Genre 1",
            bandList = LongRange(1, 10).map { ProgressItem }
        ),
        GenreModel(
            genreName = "Genre 2",
            bandList = LongRange(1, 10).map { ProgressItem }
        ),
        GenreModel(
            genreName = "Genre 3",
            bandList = LongRange(1, 10).map { ProgressItem }
        )
    )

//    private val _genreListJson = homeRepository.loadGenreListJson("")
//        .onStart { }
//        .map { }
//        .flowOn(Dispatchers.Default)
//        .catch { exeption ->
//
//        }
//
//    val genreListJson = _genreListJson

//    fun loadGenreListJson(fileName: String) = CoroutineScope(Dispatchers.IO).launch {
//        try {
//            homeRepository.loadGenreListJson(fileName).collect { data ->
//                _songData.postValue(mapResponseToModel(data))
//            }
//        } catch (e: Exception) {
//            _errorMessageLiveData.postValue(e.message)
//        }
//    }

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