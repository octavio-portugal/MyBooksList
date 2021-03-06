package com.dio.mybookslist

import android.app.Application
import com.dio.mybookslist.data.di.DataModule
import com.dio.mybookslist.presentation.di.PresentacionModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            PresentacionModule.load()
            DataModule.load()
        }
    }
}