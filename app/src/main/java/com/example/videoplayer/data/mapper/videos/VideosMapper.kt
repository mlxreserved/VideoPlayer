package com.example.videoplayer.data.mapper.videos

import com.example.videoplayer.data.model.videos.VideosDTO
import com.example.videoplayer.domain.model.videos.Videos

fun videosDTOToVideos(videosDTO: VideosDTO): Videos {
    return Videos(
        dataOfVideos = videosDTO.dataOfVideosDTO.map { dataOfVideoDTO -> dataOfVideoDTOToDataOfVideo(dataOfVideoDTO) }
    )
}