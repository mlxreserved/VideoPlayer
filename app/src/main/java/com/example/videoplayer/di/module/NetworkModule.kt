package com.example.videoplayer.di.module

import com.example.videoplayer.data.remote.client.RetrofitClient
import com.example.videoplayer.data.remote.service.VideoService
import com.example.videoplayer.data.remote.repository.VideoRemoteRepositoryImpl
import com.example.videoplayer.domain.remote.repository.VideoRemoteRepository
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
    fun provideVideoRepository(videoService: VideoService): VideoRemoteRepository {
        return VideoRemoteRepositoryImpl(videoService)
    }
}