package com.myhan.chatnel

import android.app.Application
import com.google.firebase.messaging.FirebaseMessaging
import com.myhan.chatnel.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }

        FirebaseMessaging.getInstance().subscribeToTopic("public")
    }
}