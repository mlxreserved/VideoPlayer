package com.example.videoplayer.data.model.videos

import kotlinx.serialization.Serializable


@Serializable
data class PaginationDTO(
    val currentPage: Int,
    val currentPageItems: Int,
    val itemsTotal: Int,
    val links: List<LinkDTO>,
    val pageSize: Int,
    val pagesTotal: Int
)