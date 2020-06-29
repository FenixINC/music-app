package com.example.music_app.presentation.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.music_app.R
import com.example.music_app.core.MediaPlayerCore
import com.example.music_app.core.NotificationCore
import com.example.music_app.core.utils.showSimpleErrorDialog
import com.example.music_app.presentation.adapter.SongAdapter
import com.example.music_app.presentation.constant.Constants
import com.example.music_app.core.model.Action
import com.example.music_app.presentation.model.SongModel
import com.example.music_app.presentation.service.NotificationService
import com.example.music_app.presentation.viewmodel.HomeViewModel
import com.example.music_app.presentation.viewmodel.hiltNavViewModels
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var songAdapter: SongAdapter

    @Inject
    lateinit var notificationCore: NotificationCore

    @Inject
    lateinit var mediaPlayerCore: MediaPlayerCore

    @Inject
    lateinit var storage: FirebaseStorage

    private val homeViewModel by hiltNavViewModels<HomeViewModel>()

    private var position: Int? = null

    override fun getLayoutRes() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadData()
        observeData()
        setupAdapter()
        startNotificationService()
    }

    private fun loadData() {
        homeViewModel.loadSongListFirebase()
    }

    private fun observeData() {
        homeViewModel.errorMessageLiveData.observe(viewLifecycleOwner, Observer { errorMessage ->
            showSimpleErrorDialog(
                context = context,
                title = "Error",
                message = errorMessage
            )
        })
        homeViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { isLoading ->
            setLoadingState(isLoading)
        })
        homeViewModel.songListLiveData.observe(viewLifecycleOwner, Observer { data ->
            songAdapter.songList = data.toMutableList()
        })
        homeViewModel.positionLiveData.observe(viewLifecycleOwner, Observer { position ->
            this.position = position
        })
        homeViewModel.actionLiveData.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                Action.ACTION_PREVIOUS.value -> {
                    onPreviousSong(homeViewModel.songLiveData.value!!)
                }
                Action.ACTION_NEXT.value -> {
                    onNextSong(homeViewModel.songLiveData.value!!)
                }
                Action.ACTION_PLAY.value -> {
                    if (homeViewModel.isPlayLiveData.value == true) {
                        onPauseSong(homeViewModel.songLiveData.value!!)
                        mediaPlayerCore.pauseMediaPlayer()
                    } else {
                        onPlaySong(homeViewModel.songLiveData.value!!)
                        mediaPlayerCore.createMediaPlayer(
                            requireContext(),
                            homeViewModel.songLiveData.value?.songUrl!!
                        )
                    }
                }
            }
        })
    }

    private fun setupAdapter() {
        songAdapter = SongAdapter(
            onPlayClick = { onPlaySong(it) },
            onPauseClick = { onPauseSong(it) },
            onNextClick = { onNextSong(it) },
            onPreviousClick = { onPreviousSong(it) }
        )
        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = songAdapter
    }

    private fun startNotificationService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationCore.createChannel(requireContext())
            requireActivity().registerReceiver(
                homeViewModel.broadcastReceiver,
                IntentFilter(Constants.INTENT_TRACK)
            )
            requireActivity().startService(
                Intent(
                    requireContext(),
                    NotificationService::class.java
                )
            )
        }
    }

    private fun onPlaySong(songModel: SongModel) {
        homeViewModel.setSong(songModel = songModel)
        notificationCore.createNotification(
            context = requireContext(),
            songModel = homeViewModel.songListLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_pause,
            position = position!!,
            size = homeViewModel.songListLiveData.value?.size!! - 1
        )
        homeViewModel.setIsPlay(isPlay = true)
        mediaPlayerCore.createMediaPlayer(
            requireContext(),
            songModel.songUrl!!
        )
    }

    private fun onPauseSong(songModel: SongModel) {
        homeViewModel.setSong(songModel = songModel)
        notificationCore.createNotification(
            context = requireContext(),
            songModel = homeViewModel.songListLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_play,
            position = position!!,
            size = homeViewModel.songListLiveData.value?.size!! - 1
        )
        homeViewModel.setIsPlay(isPlay = false)
        mediaPlayerCore.pauseMediaPlayer()
    }

    private fun onNextSong(songModel: SongModel) {
        homeViewModel.setSong(songModel = songModel)
        homeViewModel.setPosition(position!! + 1)
        notificationCore.createNotification(
            context = requireContext(),
            songModel = homeViewModel.songListLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_pause,
            position = position!!,
            size = homeViewModel.songListLiveData.value?.size!! - 1
        )
    }

    private fun onPreviousSong(songModel: SongModel) {
        homeViewModel.setSong(songModel = songModel)
        homeViewModel.setPosition(position!! - 1)
        notificationCore.createNotification(
            context = requireContext(),
            songModel = homeViewModel.songListLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_pause,
            position = position!!,
            size = homeViewModel.songListLiveData.value?.size!! - 1
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationCore.cancelAllNotifications()
        }
        requireActivity().unregisterReceiver(homeViewModel.broadcastReceiver)
        mediaPlayerCore.stopMediaPlayer()
    }
}