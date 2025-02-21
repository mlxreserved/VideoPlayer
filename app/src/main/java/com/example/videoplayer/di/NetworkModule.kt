package com.example.videoplayer.di

import com.example.videoplayer.data.api.RetrofitClient
import com.example.videoplayer.data.api.VideoService
import com.example.videoplayer.data.repository.VideoRepositoryImpl
import com.example.videoplayer.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideVideoService(): VideoService {
        return RetrofitClient.videoService
    }

    @Singleton
    @Provides
    fun provideVideoRepository(videoService: VideoService): VideoRepository {
        return VideoRepositoryImpl(videoService)
    }
}