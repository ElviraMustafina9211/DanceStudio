package com.example.dancestudiokisti.newSection

import com.example.dancestudiokisti.BuildConfig
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NewSectionModule {

    @Provides
    fun newSectionRepository(sectionsApi: SectionsApi): NewSectionRepository {
        return NewSectionRepository(sectionsApi)
    }

    @Provides
    fun newSectionViewModelFactory(newSectionRepository: NewSectionRepository): NewSectionViewModelFactory {
        return NewSectionViewModelFactory(newSectionRepository)
    }

    @Provides
    fun sectionsApi(gsonConverterFactory: GsonConverterFactory, rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
    ): SectionsApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(SectionsApi::class.java)
    }
}