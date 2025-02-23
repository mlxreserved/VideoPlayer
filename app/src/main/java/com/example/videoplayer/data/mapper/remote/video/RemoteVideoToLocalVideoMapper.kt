package com.example.videoplayer.data.mapper.remote.video

import com.example.videoplayer.domain.local.model.LocalVideo
import com.example.videoplayer.domain.remote.model.RemoteVideo

fun RemoteVideo.remoteVideos_to_localVideos(): LocalVideo {
    return LocalVideo(
        id = this.id,
        duration = this.duration,
        name = this.name,
        thumbnail = this.thumbnail.url,
        videoUri = this.asset.url
    )
}
