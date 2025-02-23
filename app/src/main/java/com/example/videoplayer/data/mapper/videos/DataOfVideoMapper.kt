package com.example.videoplayer.data.mapper.videos

import com.example.videoplayer.data.model.videos.DataOfVideoDTO
import com.example.videoplayer.domain.model.videos.DataOfVideo

fun dataOfVideoDTO_to_dataOfVideo(dataOfVideoDTO: DataOfVideoDTO): DataOfVideo {
    return DataOfVideo(
        duration = dataOfVideoDTO.duration,
        thumbnail = thumbnailDTO_to_thumbnail(dataOfVideoDTO.thumbnail),
        name = dataOfVideoDTO.name,
        assets = dataOfVideoDTO.assets.map { assetDTO -> assetDTO_to_asset(assetDTO) }
    )
}