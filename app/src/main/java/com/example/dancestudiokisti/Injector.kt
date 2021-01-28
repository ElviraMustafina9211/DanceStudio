package com.example.dancestudiokisti

import android.content.Context

object Injector {

    lateinit var appModule: AppComponent

    fun init(context: Context) {
        appModule = DaggerAppComponent.builder().appModule(AppModule(context)).build()
    }

    val instance: AppComponent by lazy { appModule }
}