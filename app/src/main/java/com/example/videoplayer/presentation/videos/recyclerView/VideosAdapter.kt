package com.example.videoplayer.presentation.videos.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.videoplayer.databinding.ItemVideosListBinding
import com.example.videoplayer.domain.model.video.Video
import kotlin.math.ceil

class VideosAdapter(private val items: List<Video>): RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    private var onClickListener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
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

        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, item)
        }
    }


    interface OnClickListener {
        fun onClick(position: Int, item: Video)
    }

    class VideosViewHolder(private val itemVideosListBinding: ItemVideosListBinding): RecyclerView.ViewHolder(itemVideosListBinding.root) {
        fun bind(item: Video){
            val thumbnail = item.thumbnail
            val titleVideo = item.name
            val videoDuration = formatDuration(item.duration)

            itemVideosListBinding.thumbnail.load(thumbnail)
            itemVideosListBinding.titleVideo.text = titleVideo
            itemVideosListBinding.videoDuration.text = videoDuration
        }

        private fun formatDuration(durationInSecondsWithDot: Double): String {
            val durationInSeconds = ceil(durationInSecondsWithDot).toInt()
            val hours = durationInSeconds / 3600
            val minutes = (durationInSeconds % 3600) / 60
            val seconds = durationInSeconds % 60

            return if (hours > 0) {
                // Формат "чч:мм:сс"
                String.format("%02d:%02d:%02d", hours, minutes, seconds)
            } else {
                // Формат "мм:сс"
                String.format("%02d:%02d", minutes, seconds)
            }
        }
    }
}