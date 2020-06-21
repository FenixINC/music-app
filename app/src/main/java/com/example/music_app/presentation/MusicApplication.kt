package com.example.music_app.presentation

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

@HiltAndroidApp
class MusicApplication : Application() {

    companion object {
        lateinit var sInstance: MusicApplication
            private set

        val getAppInstance: MusicApplication
            get() = sInstance
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this
        Stetho.initializeWithDefaults(this@MusicApplication)
        Timber.plant(Timber.DebugTree())
//        setKoin()
    }

    private fun setKoin() = startKoin {
        androidContext(this@MusicApplication)
        modules(
            listOf(
//                viewModelModule,
//                repositoryModule,
//                networkModule,
//                serviceModule,
//                databaseModule,
//                daoModule
            )
        )
    }
}