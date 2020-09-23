package com.example.music_app.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.music_app.databinding.ListItemGenreBinding
import com.example.music_app.presentation.model.GenreModel
import com.example.music_app.presentation.model.SongModel

class OldGenreAdapter(
    private val listener: (SongModel) -> Unit
) : RecyclerView.Adapter<OldGenreAdapter.ViewHolder>() {

//    private val diffUtil = object : DiffUtil.ItemCallback<GenreModel>() {
//        override fun areItemsTheSame(oldItem: GenreModel, newItem: GenreModel): Boolean {
//            return oldItem === newItem
//        }
//
//        override fun areContentsTheSame(oldItem: GenreModel, newItem: GenreModel): Boolean {
//            return oldItem.genre == newItem.genre
//        }
//    }

    var genreList = mutableListOf<GenreModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = genreList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = ListItemGenreBinding.inflate(inflater, parent, false)
        return ViewHolder(holder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        getItem(position)?.name?.let { genre ->
//            holder.bind(genre = genre)
//        }
//        val songList = songList
//        val songAdapter = SongAdapter(listener)
//        val childLayoutManager = LinearLayoutManager(
//            holder.itemView.context,
//            RecyclerView.HORIZONTAL,
//            false
//        )
//
//        holder.genre.text = genre
//        holder.horizontalRecyclerView.apply {
//            layoutManager = LinearLayoutManager(
//                holder.horizontalRecyclerView.context,
//                RecyclerView.HORIZONTAL,
//                false
//            )
//            adapter = songAdapter
//
//            val diffUtilCallback = SongAdapter.DiffUtilCallback(songAdapter.songList, songList)
//            val diffUtilResult = DiffUtil.calculateDiff(diffUtilCallback)
//            songAdapter.songList = songList
//            diffUtilResult.dispatchUpdatesTo(songAdapter)
//        }

        val genreModel = genreList[position]
        val songList = genreList[position].songList

        val songAdapter = OldSongAdapter(listener)
//        songAdapter.songList = songList!!.toMutableList()

        holder.bind(genre = genreModel.genre!!, oldSongAdapter = songAdapter)
    }

    class ViewHolder(private val binding: ListItemGenreBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            // TODO: For click listener
        }

        fun bind(genre: String, oldSongAdapter: OldSongAdapter) = with(binding) {
            setGenre(genre)
//            rvSongList.setHasFixedSize(true)
//            rvSongList.layoutManager = LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
//            rvSongList.adapter = songAdapter
        }
    }
}