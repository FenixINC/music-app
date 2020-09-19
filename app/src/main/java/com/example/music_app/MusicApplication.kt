package com.example.music_app

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.regex.Pattern

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

        val text = getTextOnly("Dima1243(4 * 3!) * 56 + is9r7 Android (37 - 13) developer")
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

    private fun getTextOnly(text: String): String {
        val specSymbols = " !#$%&'()*+,-./:;<=>?@[]^_`{|}0123456789"
        var resultText = ""

        for ((position: Int, item: Char) in text.withIndex()) {
            if (specSymbols.contains(text[position])) {
                continue
            } else {
                resultText += item
            }
        }

        return resultText

//      Pattern.compile("([0-9])\\s+([*/+-])").split(text)
//      Pattern.compile("([\$&+,:;=\\\\?@#|/'<>.^*()%!-])").split(text)
//        val regex = "([0-9])\\*/-+"
//        val pattern = Pattern.compile(regex)
//        val matcher = pattern.matcher(text)
//
//        if (matcher.matches()) {
//            matcher.
//        }
//        return pattern.split(text)
    }
}