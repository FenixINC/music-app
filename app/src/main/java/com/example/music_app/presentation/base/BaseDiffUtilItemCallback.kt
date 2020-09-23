package com.example.music_app.presentation.base

import androidx.recyclerview.widget.DiffUtil
import com.example.music_app.presentation.model.ListItem

open class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean =
        oldItem.equals(newItem)
}