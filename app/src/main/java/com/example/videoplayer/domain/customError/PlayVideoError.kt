package com.example.videoplayer.domain.customError

sealed class PlayVideoError {
    // Ошибки сети
    object NetworkError : PlayVideoError()
    object TimeoutError : PlayVideoError()

    // HTTP-ошибки
    data class HttpError(
        val code: Int,
        val message: String
    ) : PlayVideoError()

    // Ошибки воспроизведения
    object DecodingError : PlayVideoError()

    data class UnknownError(
        val cause: Throwable?
    ) : PlayVideoError()
}