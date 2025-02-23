package com.example.videoplayer.di

import android.content.Context
import androidx.media3.exoplayer.ExoPlayer
import com.example.videoplayer.data.player.ExoVideoPlayer
import com.example.videoplayer.domain.player.VideoPlayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object VideoModule {

    @Provides
    @Singleton
    fun provideVideoPlayer(@ApplicationContext context: Context): VideoPlayer {
        return ExoVideoPlayer(context)
    }


}