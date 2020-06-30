package com.example.music_app.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseRecyclerAdapter<T, V : RecyclerView.ViewHolder> : RecyclerView.Adapter<V>() {
    var items: MutableList<T> = ArrayList()

    override fun getItemCount(): Int = items.size

    fun getItem(position: Int): T? = items[position]

    fun add(item: T) {
        items.add(item)
        notifyDataSetChanged()
    }

    fun addAll(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceAll(items: List<T>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun removeItem(item: T) {
        items.remove(item)
        notifyDataSetChanged()
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

    fun itemPosition(item: T): Int {
        return items.lastIndexOf(item)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}