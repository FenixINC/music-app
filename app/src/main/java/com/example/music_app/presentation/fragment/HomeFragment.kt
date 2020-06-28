package com.example.music_app.presentation.fragment

import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.music_app.R
import com.example.music_app.presentation.constant.Constants
import com.example.music_app.presentation.core.NotificationCore
import com.example.music_app.presentation.listener.TrackListener
import com.example.music_app.presentation.model.Action
import com.example.music_app.presentation.service.NotificationService
import com.example.music_app.presentation.viewmodel.HomeViewModel
import com.example.music_app.presentation.viewmodel.hiltNavViewModels
import com.example.music_app.utils.showSimpleErrorDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(), TrackListener {

    @Inject
    lateinit var notificationCore: NotificationCore

    private val homeViewModel by hiltNavViewModels<HomeViewModel>()

    private var position: Int? = null

    override fun getLayoutRes() = R.layout.fragment_home

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeData()
        loadData()
        startNotificationService()

        btn_play.setOnClickListener {
            if (homeViewModel.isPlayLiveData.value == true) {
                onPauseClick()
            } else {
                onPlayClick()
            }
        }
    }

    private fun loadData() {
        homeViewModel.loadTrackList()
    }

    private fun observeData() {
        homeViewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { isLoading ->
            setLoadingState(isLoading)
        })
        homeViewModel.trackLiveData.observe(viewLifecycleOwner, Observer { data ->
            val response = data
            // homeAdapter.setList(data)
        })
        homeViewModel.errorMessageLiveData.observe(viewLifecycleOwner, Observer { errorMessage ->
            showSimpleErrorDialog(context = context, title = "Error", message = errorMessage)
        })
        homeViewModel.positionLiveData.observe(viewLifecycleOwner, Observer { position ->
            this.position = position
        })
        homeViewModel.actionLiveData.observe(viewLifecycleOwner, Observer { action ->
            when (action) {
                Action.ACTION_PREVIOUS.value -> {
                    onPreviousClick()
                }
                Action.ACTION_NEXT.value -> {
                    onNextClick()
                }
                Action.ACTION_PLAY.value -> {
                    if (homeViewModel.isPlayLiveData.value == true) {
                        onPauseClick()
                    } else {
                        onPlayClick()
                    }
                }
            }
        })
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

    override fun onPlayClick() {
        notificationCore.createNotification(
            context = requireContext(),
            track = homeViewModel.trackLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_pause,
            position = position!!,
            size = homeViewModel.trackLiveData.value?.size!! - 1
        )
        homeViewModel.setIsPlay(isPlay = true)
    }

    override fun onPauseClick() {
        notificationCore.createNotification(
            context = requireContext(),
            track = homeViewModel.trackLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_play,
            position = position!!,
            size = homeViewModel.trackLiveData.value?.size!! - 1
        )
        homeViewModel.setIsPlay(isPlay = false)
    }

    override fun onNextClick() {
        homeViewModel.setPosition(position!! + 1)
        notificationCore.createNotification(
            context = requireContext(),
            track = homeViewModel.trackLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_pause,
            position = position!!,
            size = homeViewModel.trackLiveData.value?.size!! - 1
        )
    }

    override fun onPreviousClick() {
        homeViewModel.setPosition(position!! - 1)
        notificationCore.createNotification(
            context = requireContext(),
            track = homeViewModel.trackLiveData.value?.get(position!!)!!,
            drawableActionButton = R.drawable.ic_pause,
            position = position!!,
            size = homeViewModel.trackLiveData.value?.size!! - 1
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationCore.cancelAllNotifications()
        }
        requireActivity().unregisterReceiver(homeViewModel.broadcastReceiver)
    }
}