package com.example.dancestudiokisti

import dagger.Component
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Component(modules = [AppModule::class])
interface AppComponent {
    fun gsonConverterFactory(): GsonConverterFactory
    fun rxJavaAdapter(): RxJava2CallAdapterFactory
}