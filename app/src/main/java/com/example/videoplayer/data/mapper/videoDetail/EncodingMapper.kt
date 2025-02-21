package com.example.videoplayer.data.mapper.videoDetail

import com.example.videoplayer.data.model.videoDetail.EncodingDTO
import com.example.videoplayer.domain.model.videoDetail.Encoding

fun encodingDTOToEncoding (encodingDTO: EncodingDTO): Encoding {
    return Encoding(
        metadata = metadataVideoDetailDTOToMetadataVideoDetail(encodingDTO.metadata),
    )
}