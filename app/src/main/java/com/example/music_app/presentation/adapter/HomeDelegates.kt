package com.example.music_app.presentation.adapter

import com.example.music_app.databinding.ListItemGenreBinding
import com.example.music_app.databinding.ListItemProgressBinding
import com.example.music_app.databinding.ListItemSongBinding
import com.example.music_app.presentation.model.GenreModel
import com.example.music_app.presentation.model.ListItem
import com.example.music_app.presentation.model.ProgressItem
import com.example.music_app.presentation.model.SongModel
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

object HomeDelegates {

    val songHorizontalDelegate =
        adapterDelegateViewBinding<GenreModel, ListItem, ListItemGenreBinding>(
            { inflater, container ->
                ListItemGenreBinding.inflate(inflater, container, false)
            }
        ) {
            // onCreateVewHolder
            binding.rvSong.adapter = ListDelegationAdapter(
                songItemDelegate,
                progressItemDelegate
            )

            // onBindViewHolder
            bind {
                binding.genre = item.genre
                (binding.rvSong.adapter as ListDelegationAdapter<List<ListItem>>).apply {
                    items = item.songList
                    notifyDataSetChanged()
                }
            }

            // onViewRecycler
            onViewRecycled {

            }
        }

    private val songItemDelegate =
        adapterDelegateViewBinding<SongModel, ListItem, ListItemSongBinding>(
            { inflater, container ->
                ListItemSongBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                binding.model = item
                binding.imageSong.setBackgroundColor(item.hashCode())
                binding.executePendingBindings()
            }
        }

    private val progressItemDelegate =
        adapterDelegateViewBinding<ProgressItem, ListItem, ListItemProgressBinding>(
            { inflater, container -> ListItemProgressBinding.inflate(inflater, container, false) }
        ) {}
}