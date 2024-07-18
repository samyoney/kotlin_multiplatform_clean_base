package org.sam.multiplatform_base

import android.app.Application
import initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import timber.log.Timber

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin({
            Timber.plant(Timber.DebugTree())
            androidLogger()
            androidContext(this@MainApp)
        }, { message ->
            Timber.d(message)
        })
    }
}