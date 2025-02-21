package com.example.videoplayer.data.mapper.videos

import com.example.videoplayer.data.model.videos.DataOfVideoDTO
import com.example.videoplayer.domain.model.videos.DataOfVideo

fun dataOfVideoDTOToDataOfVideo(dataOfVideoDTO: DataOfVideoDTO): DataOfVideo {
    return DataOfVideo(
        assets = assetsDTOTOAssets(dataOfVideoDTO.assets),
        title = dataOfVideoDTO.title,
        videoId = dataOfVideoDTO.videoId
    )
}