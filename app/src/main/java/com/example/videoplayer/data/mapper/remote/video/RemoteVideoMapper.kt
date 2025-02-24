package com.example.videoplayer.data.mapper.remote.video

import com.example.videoplayer.data.remote.model.RemoteVideoDTO
import com.example.videoplayer.domain.model.video.Video
import com.example.videoplayer.domain.model.video.Asset
import com.example.videoplayer.util.Constant.SECURED_URL_START
import com.example.videoplayer.util.Constant.UNSECURED_URL_START

fun remoteVideoDTO_to_video(remoteVideoDTO: RemoteVideoDTO): Video {
    val assets: List<Asset> = remoteVideoDTO.assets.map { assetDTO -> assetDTO_to_asset(assetDTO) }
    val necessaryAsset = findNecessaryAsset(assets = assets)
    val correctAsset = necessaryAsset.copy(url = createCorrectUri(necessaryAsset.url))

    return Video(
        id = remoteVideoDTO.id,
        duration = remoteVideoDTO.duration,
        thumbnail = remoteVideoDTO.thumbnail.url,
        name = remoteVideoDTO.name,
        asset = correctAsset
    )
}

fun video_to_remoteVideoDTO(video: Video): RemoteVideoDTO {
    return RemoteVideoDTO(
        id = video.id,
        duration = video.duration,
        thumbnail = thumbnail_to_thumbnailDTO(video.thumbnail),
        name = video.name,
        assets = listOf(asset_to_assetDTO(video.asset))
    )
}

private fun findNecessaryAsset(assets: List<Asset>): Asset {
    return assets.find { asset ->
        asset.contentType == "video/mp4" && asset.type == "MdMp4VideoFile"
                || asset.contentType == "video/mp4" && asset.type == "HdMp4VideoFile"
    } ?: Asset(
        url = assets[0].url,
        type = assets[0].type,
        contentType = assets[0].contentType
    )
}


private fun createCorrectUri(requiredUriMediaItem: String): String {
    if(requiredUriMediaItem.substring(0, 6) != SECURED_URL_START) {
        return requiredUriMediaItem.replace(UNSECURED_URL_START, SECURED_URL_START)
    }
    return requiredUriMediaItem
}

