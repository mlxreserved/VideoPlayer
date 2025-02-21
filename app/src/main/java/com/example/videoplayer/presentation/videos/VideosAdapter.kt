package com.example.videoplayer.presentation.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.videoplayer.databinding.ItemVideosListBinding
import com.example.videoplayer.domain.model.videos.DataOfVideo
import com.example.videoplayer.domain.model.videos.Videos

class VideosAdapter(private val items: List<DataOfVideo>): RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    class VideosViewHolder(private val itemVideosListBinding: ItemVideosListBinding): RecyclerView.ViewHolder(itemVideosListBinding.root) {
        fun bind(item: DataOfVideo){
            val thumbnail = item.assets.thumbnail
            val titleVideo = item.title
            val videoDuration = item.duration.toString()
            itemVideosListBinding.thumbnail.load(thumbnail)
            itemVideosListBinding.titleVideo.text = titleVideo
            itemVideosListBinding.videoDuration.text = videoDuration
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemVideosListBinding = ItemVideosListBinding.inflate(layoutInflater, parent, false)
        return VideosViewHolder(itemVideosListBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

}