package com.example.videoplayer.data.mapper.videoDetail

import com.example.videoplayer.data.model.videoDetail.VideoDetailDTO
import com.example.videoplayer.domain.model.videoDetail.VideoDetail

fun videoDetailDTOToVideoDetail(videoDetailDTO: VideoDetailDTO): VideoDetail {
    return VideoDetail(
        encoding = encodingDTOToEncoding(videoDetailDTO.encoding)
    )
}