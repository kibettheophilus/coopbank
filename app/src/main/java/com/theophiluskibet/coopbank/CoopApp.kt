package com.theophiluskibet.coopbank

import android.app.Application
import com.theophiluskibet.coopbank.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoopApp)
            androidLogger()
            modules(listOf(appModule))
        }
    }
}