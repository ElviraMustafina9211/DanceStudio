package com.example.dancestudiokisti

import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun gsonConverterFactory(): GsonConverterFactory
    fun rxJavaAdapter(): RxJava2CallAdapterFactory
    fun tokenSaver(): TokenSaver
    fun httpClient(): OkHttpClient
}