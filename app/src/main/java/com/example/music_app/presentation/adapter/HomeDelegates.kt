package com.example.music_app.presentation.adapter

import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.music_app.R
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
            val adapter = BandsAdapter()
            binding.recyclerViewBands.adapter = adapter

            // onBindViewHolder
            bind {
                binding.genre = item.genreName
                adapter.items = item.bandList
            }

            // onViewRecycler
            onViewRecycled {

            }
        }

    val bandItemDelegate =
        adapterDelegateViewBinding<BandModel, ListItem, ListItemBandBinding>(
            { inflater, container ->
                ListItemBandBinding.inflate(inflater, container, false)
            }
        ) {
            bind {
                val resources = binding.root.resources
                Glide.with(binding.root)
                    .load(item.imageUrl)
                    .override(
                        resources.getDimensionPixelOffset(R.dimen.image_size_150dp),
                        resources.getDimensionPixelOffset(R.dimen.image_size_150dp)
                    )
                    .transform(
                        CenterCrop(),
                        RoundedCorners(resources.getDimensionPixelOffset(R.dimen.image_size_36dp))
                    )
                    .into(binding.imageBand)

                binding.model = item
                binding.executePendingBindings()
            }
        }

    val progressItemDelegate =
        adapterDelegateViewBinding<ProgressItem, ListItem, ListItemProgressBinding>(
            { inflater, container -> ListItemProgressBinding.inflate(inflater, container, false) }
        ) {}
}