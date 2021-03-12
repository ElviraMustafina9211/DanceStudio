package com.example.dancestudiokisti.imagePicker

import com.example.dancestudiokisti.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ImageModule {

    @Provides
    fun imageRepository(imageApi: ImageApi): ImageRepository {
        return ImageRepositoryImpl(imageApi)
    }

    @Provides
    fun imageViewModelFactory(imageRepository: ImageRepository): ImageViewModelFactory {
        return ImageViewModelFactory(imageRepository)
    }

    @Provides
    fun imageApi(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): ImageApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(ImageApi::class.java)
    }
}