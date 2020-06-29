package com.example.music_app.di

import android.app.Notification
import com.example.music_app.core.MediaPlayerCore
import com.example.music_app.core.NotificationCore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(value = [ApplicationComponent::class])
object ModuleCore {

    @Provides
    fun provideNotification() = Notification()

    @Provides
    @Singleton
    fun provideNotificationCore(notification: Notification) = NotificationCore(notification)

    @Provides
    @Singleton
    fun provideMediaPlayerCore() = MediaPlayerCore()
}
