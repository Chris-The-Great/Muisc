package com.example.ccmusichomework.views

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ccmusichomework.R
import com.example.ccmusichomework.databinding.MusicEventBinding
import com.example.ccmusichomework.model.Song
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.squareup.picasso.Picasso

class Adapter (
    private val songData : MutableList<Song> = mutableListOf(),
    private val SongClick : (Song) -> Unit
        ): RecyclerView.Adapter<SongType>()
{

    private var userListSong : List<Song> = emptyList()

    /**
     * Fuction for adding songs to the recuyleview.
     */
    fun updateSongs(newSongs: List<Song>)
    {
        songData.clear()
        newSongs.forEach{
            songData.add(it)
        }
        Log.d("data", songData.size.toString())
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongType =
        SongType(
            MusicEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = songData.size

    override fun onBindViewHolder(holder: SongType, position: Int) =
        holder.bind(songData[position],SongClick)




}

class SongType (private val binding : MusicEventBinding): RecyclerView.ViewHolder(binding.root) {

    /**
     * Here we set up for the exoplayer by getting the vaule of context from
     * each item view.
     */
    val context = itemView.context


    var curSong: Song? = null
    fun bind(songData: Song, SongClick: (Song) -> Unit) {
        binding.artistName.text = songData.artistName
        binding.collectionName.text = songData.collectionName
        binding.trackPrice.text = songData.trackPrice.toString()

        /**
         * Calling the picasso method to add the image to view.
         *
         */
        Picasso.get()
            .load(songData.artworkUrl60)
            .placeholder(R.drawable.image_loading)
            .error(R.drawable.image_error)
            .into(binding.artWork)

        /**
         * On the click listener set the songPlayer varable to the exoplayer
         * using the context val made ealier
         * then adds it the media item so it can be called and played.
         */

        binding.root.setOnClickListener {
            val songPlayer: ExoPlayer = ExoPlayer.Builder(context.applicationContext).build()
            songPlayer.addMediaItem(MediaItem.fromUri(songData.previewUrl))
            songPlayer.prepare()
            songPlayer.play()

        }


    }
}


