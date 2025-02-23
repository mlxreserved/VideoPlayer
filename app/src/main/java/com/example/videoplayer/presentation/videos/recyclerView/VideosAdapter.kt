package com.example.videoplayer.presentation.videos.recyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil3.load
import com.example.videoplayer.databinding.ItemVideosListBinding
import com.example.videoplayer.domain.model.videos.DataOfVideo
import kotlin.math.ceil

class VideosAdapter(private val items: List<DataOfVideo>): RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

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
        fun onClick(position: Int, item: DataOfVideo)
    }

    class VideosViewHolder(private val itemVideosListBinding: ItemVideosListBinding): RecyclerView.ViewHolder(itemVideosListBinding.root) {
        fun bind(item: DataOfVideo){
            val thumbnail = item.thumbnail.url
            val titleVideo = item.name
            val videoDuration = formatDuration(item.duration)

            itemVideosListBinding.thumbnail.load(thumbnail)
            itemVideosListBinding.titleVideo.text = titleVideo
            itemVideosListBinding.videoDuration.text = videoDuration
        }

//        private fun parseDuration(videoDuration: String): String {
//            val regexToParseISO8601 = Regex("PT(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?")
//            val matchResult = regexToParseISO8601.find(videoDuration)
//            val hours = matchResult?.groupValues?.get(1)?.toIntOrNull() ?: 0
//            val minutes = matchResult?.groupValues?.get(2)?.toIntOrNull() ?: 0
//            val seconds = matchResult?.groupValues?.get(3)?.toIntOrNull() ?: 0
//
//            return if(hours > 0) {
//                String.format("%02d:%02d:%02d", hours, minutes, seconds) // ЧЧ:ММ:СС
//            } else {
//                String.format("%02d:%02d", minutes, seconds) // ЧЧ:ММ:СС
//            }
//        }
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