package com.example.music_app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_app.presentation.model.SongModel

class OldSongAdapter(
    private val clickListener: (SongModel) -> Unit
) /*: RecyclerView.Adapter<SongListAdapter.ViewHolder>() {

//    class DiffUtilCallback(
//        private val oldList: List<SongModel>,
//        private val newList: List<SongModel>
//    ) : DiffUtil.Callback() {
//        override fun getOldListSize() = oldList.size
//        override fun getNewListSize() = newList.size
//        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
//            oldList[oldItemPosition].id == newList[newItemPosition].id
//
//        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
//            oldList[oldItemPosition] == newList[newItemPosition]
//    }

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

    class ViewHolder(private val binding: ListItemSongBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            songModel: SongModel,
            clickListener: (SongModel) -> Unit
        ) = with(binding) {
            model = songModel
//            icPlay.setOnClickListener { clickListener(songModel, Action.ACTION_PLAY.value) }
        }
    }
}*/