package com.example.music_app.presentation.adapter

import com.example.music_app.databinding.ListItemBandBinding
import com.example.music_app.databinding.ListItemGenreBinding
import com.example.music_app.databinding.ListItemProgressBinding
import com.example.music_app.presentation.model.BandModel
import com.example.music_app.presentation.model.GenreModel
import com.example.music_app.presentation.model.ListItem
import com.example.music_app.presentation.model.ProgressItem
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object HomeDelegates {

    val songHorizontalDelegate =
        adapterDelegateViewBinding<GenreModel, ListItem, ListItemGenreBinding>(
            { inflater, container ->
                ListItemGenreBinding.inflate(inflater, container, false)
            }
        ) {
            // onCreateVewHolder
            val adapter = SongAdapter()
            binding.rvSong.adapter = adapter

            // onBindViewHolder
            bind {
                binding.genre = item.genre
                adapter.items = item.songList
            }

            // onViewRecycler
            onViewRecycled {

            }
        }

    val songItemDelegate =
        adapterDelegateViewBinding<BandModel, ListItem, ListItemBandBinding>(
            { inflater, container ->
                ListItemBandBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.model = item
                binding.imageSong.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
            }
        }

    val progressItemDelegate =
        adapterDelegateViewBinding<ProgressItem, ListItem, ListItemProgressBinding>(
            { inflater, container -> ListItemProgressBinding.inflate(inflater, container, false) }
        ) {}
}