package com.example.videoplayer.data.mapper.local.video

import com.example.videoplayer.data.local.entity.VideoEntity
import com.example.videoplayer.domain.local.model.LocalVideo

fun videoEntity_to_video(videoEntity: VideoEntity): LocalVideo {
    return LocalVideo(
        id = videoEntity.id,
        duration = videoEntity.duration,
        videoUri = videoEntity.videoUri,
        name = videoEntity.name,
        thumbnail = videoEntity.thumbnail
    )
}

fun video_to_videoEntity(video: LocalVideo): VideoEntity {
    return VideoEntity(
        id = video.id,
        duration = video.duration,
        videoUri = video.videoUri,
        name = video.name,
        thumbnail = video.thumbnail
    )
}