package com.example.videoplayer.data.mapper.local.video

import com.example.videoplayer.data.local.entity.VideoEntity
import com.example.videoplayer.domain.model.video.Video

fun videoEntity_to_video(videoEntity: VideoEntity): Video {
    return Video(
        id = videoEntity.id,
        duration = videoEntity.duration,
        asset = assetEntity_to_asset(videoEntity.asset),
        name = videoEntity.name,
        thumbnail = videoEntity.thumbnail
    )
}

fun video_to_videoEntity(video: Video): VideoEntity {
    return VideoEntity(
        id = video.id,
        duration = video.duration,
        asset = asset_to_assetEntity(video.asset),
        name = video.name,
        thumbnail = video.thumbnail
    )
}