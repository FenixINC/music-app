package com.example.music_app.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.music_app.R
import com.example.music_app.core.MediaPlayerCore
import com.example.music_app.core.NotificationCore
import com.example.music_app.databinding.FragmentHomeBinding
import com.example.music_app.presentation.adapter.GenreAdapter
import com.example.music_app.presentation.base.hiltNavViewModels
import com.example.music_app.utils.viewBinding
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding { FragmentHomeBinding.bind(it) }

    private val adapter = GenreAdapter()

    @Inject
    lateinit var notificationCore: NotificationCore

    @Inject
    lateinit var mediaPlayerCore: MediaPlayerCore

    @Inject
    lateinit var storage: FirebaseStorage

    private val homeViewModel by hiltNavViewModels<HomeViewModel>()

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
////        loadData()
//        observeData()
//        setupAdapter()
//        startNotificationService()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val jsonFileString = getJsonDataFromAsset(activity?.applicationContext!!, "genre_list.json.json")
//
//        val gson = Gson()
//        val genreListType = object : TypeToken<List<GenreResponse>>() {}.type
//
//        var genreList: List<GenreResponse> = gson.fromJson(jsonFileString, genreListType)
//        genreList.forEachIndexed { index, genreResponse ->
//            val result = genreResponse
//        }

//        homeViewModel.loadGenreListJson(
//            context?.resources?.openRawResource(R.raw.genre_list)?.bufferedReader()
//                .use { it?.readText()!! })

        lifecycleScope.launchWhenStarted {
//            homeViewModel.genreListJson.collect {
//
//            }
        }

        with(binding) {
            recyclerView.adapter = adapter

            homeViewModel.songData.observe(viewLifecycleOwner, { genreList ->
                adapter.items = genreList
            })

        }
    }

//    private fun loadData() {
//        homeViewModel.loadSongListFirebase()
//    }
//
//    private fun observeData() {
//        homeViewModel.errorMessageLiveData.observe(viewLifecycleOwner, { errorMessage ->
//            showSimpleErrorDialog(
//                context = context,
//                title = "Error",
//                message = errorMessage
//            )
//        })
//        homeViewModel.loadingLiveData.observe(viewLifecycleOwner, { isLoading ->
////            setLoadingState(isLoading)
//        })
//        homeViewModel.songListLiveData.observe(viewLifecycleOwner, { data ->
////            genreAdapter.genreList = data.toMutableList()
//        })
//        homeViewModel.actionPositionLiveData.observe(viewLifecycleOwner, { position ->
//            homeViewModel.positionAction = position
//        })
//        homeViewModel.actionLiveData.observe(viewLifecycleOwner, { action ->
//            when (action) {
//                Action.ACTION_PREVIOUS.value -> {
//                    onPreviousSong(homeViewModel.songLiveData.value!!)
//                }
//                Action.ACTION_NEXT.value -> {
//                    onNextSong(homeViewModel.songLiveData.value!!)
//                }
//                Action.ACTION_PLAY.value -> {
//                    if (homeViewModel.isPlayLiveData.value == true) {
//                        onPauseSong(homeViewModel.songLiveData.value!!)
//                        mediaPlayerCore.pauseMediaPlayer()
//                    } else {
//                        onPlaySong(homeViewModel.songLiveData.value!!)
//                        mediaPlayerCore.createMediaPlayer(
//                            homeViewModel.songLiveData.value?.songUrl!!,
//                            homeViewModel.completionListener
//                        )
//                    }
//                }
//            }
//        })
//    }
//
//    private fun setupAdapter() {
////        songAdapter = SongAdapter(clickListener = { songModel: SongModel/*, action: String */ ->
//////            onClickListener(songModel, action)
////        })
////        recycler_view.layoutManager = LinearLayoutManager(activity)
////        recycler_view.adapter = songAdapter
//
//        with(binding) {
//            recyclerView.adapter = adapter
//
//            adapter.apply {
//                items = listOf(
//                    GenreModel(
//                        genre = "Genre 1",
//                        songList = IntRange(1, 20).map { SongModel(it, "Title $it") }
//                    )
//                )
//                notifyDataSetChanged()
//            }
//        }
//
////        adapter.apply {
////            items = listOf(
////                GenreModel(
////                    "Genre 1",
////                    listOf(SongModel(name = "Song 1"), SongModel(name = "Song 2"))
////                )
////            )
////            notifyDataSetChanged()
////        }
//    }
//
//    private fun startNotificationService() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationCore.createChannel(requireContext())
//            requireActivity().registerReceiver(
//                homeViewModel.broadcastReceiver,
//                IntentFilter(NotificationConstants.INTENT_TRACK)
//            )
//            requireActivity().startService(
//                Intent(
//                    requireContext(),
//                    NotificationService::class.java
//                )
//            )
//        }
//    }
//
//    private fun onClickListener(songModel: SongModel, action: String) {
//        when (action) {
//            Action.ACTION_PLAY.value -> {
//                onPlaySong(songModel)
//            }
//            Action.ACTION_PAUSE.value -> {
//                onPauseSong(songModel)
//            }
//            Action.ACTION_NEXT.value -> {
//                onNextSong(songModel)
//            }
//            Action.ACTION_PREVIOUS.value -> {
//                onPreviousSong(songModel)
//            }
//        }
//    }
//
//    private fun onPlaySong(songModel: SongModel) {
//        homeViewModel.setSong(songModel = songModel)
//        notificationCore.createNotification(
//            context = requireContext(),
//            songModel = homeViewModel.songListLiveData.value?.get(homeViewModel.positionAction)!!,
//            drawableActionButton = R.drawable.ic_pause,
//            actionPosition = homeViewModel.positionAction,
//            size = homeViewModel.songListLiveData.value?.size!! - 1
//        )
//        homeViewModel.setIsPlay(isPlay = true)
//        mediaPlayerCore.createMediaPlayer(
//            songModel.songUrl!!,
//            homeViewModel.completionListener
//        )
//    }
//
//    private fun onPauseSong(songModel: SongModel) {
//        homeViewModel.setSong(songModel = songModel)
//        notificationCore.createNotification(
//            context = requireContext(),
//            songModel = homeViewModel.songListLiveData.value?.get(homeViewModel.positionAction)!!,
//            drawableActionButton = R.drawable.ic_play,
//            actionPosition = homeViewModel.positionAction,
//            size = homeViewModel.songListLiveData.value?.size!! - 1
//        )
//        homeViewModel.setIsPlay(isPlay = false)
//        mediaPlayerCore.pauseMediaPlayer()
//    }
//
//    private fun onNextSong(songModel: SongModel) {
//        homeViewModel.setSong(songModel = songModel)
//        homeViewModel.setActionPosition(homeViewModel.positionAction + 1)
//        notificationCore.createNotification(
//            context = requireContext(),
//            songModel = homeViewModel.songListLiveData.value?.get(homeViewModel.positionAction)!!,
//            drawableActionButton = R.drawable.ic_pause,
//            actionPosition = homeViewModel.positionAction,
//            size = homeViewModel.songListLiveData.value?.size!! - 1
//        )
//    }
//
//    private fun onPreviousSong(songModel: SongModel) {
//        homeViewModel.setSong(songModel = songModel)
//        homeViewModel.setActionPosition(homeViewModel.positionAction - 1)
//        notificationCore.createNotification(
//            context = requireContext(),
//            songModel = homeViewModel.songListLiveData.value?.get(homeViewModel.positionAction)!!,
//            drawableActionButton = R.drawable.ic_pause,
//            actionPosition = homeViewModel.positionAction,
//            size = homeViewModel.songListLiveData.value?.size!! - 1
//        )
//    }

//    override fun onDestroy() {
//        super.onDestroy()
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            notificationCore.cancelAllNotifications()
//        }
//        requireActivity().unregisterReceiver(homeViewModel.broadcastReceiver)
//        mediaPlayerCore.stopMediaPlayer()
//    }

//    override fun onBackPressed() {
//        super.onBackPressed()
//        activity?.finish()
//    }
}