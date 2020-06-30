package com.example.music_app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_app.core.model.Action
import com.example.music_app.databinding.ListItemSongBinding
import com.example.music_app.presentation.model.SongModel

class SongAdapter(
    private val clickListener: (SongModel, String) -> Unit
) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    var songList = mutableListOf<SongModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun setIsPlay(songModel: SongModel) {
        notifyItemChanged(getPositionItemById(songModel.id))
    }

    private fun getPositionItemById(id: Int?): Int {
        var result = -1
        for ((position: Int, song: SongModel) in songList.withIndex()) {
            if (song.id == id) {
                result = position
                break
            }
        }
        return result
    }

    override fun getItemCount() = songList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = ListItemSongBinding.inflate(inflater, parent, false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(songModel = songList[position], clickListener = clickListener)
    }

    class ViewHolder(private val binding: ListItemSongBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            songModel: SongModel,
            clickListener: (SongModel, String) -> Unit
        ) = with(binding) {
            model = songModel
            icPlay.setOnClickListener { clickListener(songModel, Action.ACTION_PLAY.value) }
        }
    }
}