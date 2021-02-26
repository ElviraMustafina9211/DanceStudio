package com.example.dancestudiokisti.sectionsList

import com.example.dancestudiokisti.BuildConfig
import com.example.dancestudiokisti.newSection.SectionsApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class SectionsListModule {

    @Provides
    fun sectionsListRepository(sectionsApi: SectionsApi): SectionsListRepository {
        return SectionsListRepository(sectionsApi)
    }

    @Provides
    fun sectionsListViewModelFactory(sectionsListRepository: SectionsListRepository): SectionsListViewModelFactory {
        return SectionsListViewModelFactory(sectionsListRepository)
    }

    @Provides
    fun sectionsApi(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): SectionsApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(SectionsApi::class.java)
    }
}