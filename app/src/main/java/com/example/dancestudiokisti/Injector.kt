package com.example.dancestudiokisti

object Injector {
    val instance: AppComponent by lazy { DaggerAppComponent.create() }
}