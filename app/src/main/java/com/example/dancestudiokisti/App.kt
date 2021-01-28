package com.example.dancestudiokisti

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}