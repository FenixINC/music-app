package com.example.music_app.presentation.adapter

import com.example.music_app.presentation.base.BaseDiffUtilItemCallback
import com.example.music_app.presentation.model.ListItem
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class GenreAdapter : AsyncListDifferDelegationAdapter<ListItem>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(HomeDelegates.songHorizontalDelegate)
    }
}