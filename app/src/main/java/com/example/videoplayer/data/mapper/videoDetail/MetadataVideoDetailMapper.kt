package com.example.videoplayer.data.mapper.videoDetail

import com.example.videoplayer.data.model.videoDetail.MetadataVideoDetailDTO
import com.example.videoplayer.domain.model.videoDetail.MetadataVideoDetail

fun metadataVideoDetailDTOToMetadataVideoDetail(metadataVideoDetailDTO: MetadataVideoDetailDTO): MetadataVideoDetail {
    return MetadataVideoDetail(
        duration = metadataVideoDetailDTO.duration
    )
}