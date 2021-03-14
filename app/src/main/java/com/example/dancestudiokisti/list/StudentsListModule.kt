package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class StudentsListModule {

    @Provides
    fun studentsListRepository(studentsListApi: StudentsListApi): StudentsListRepository {
        return StudentsListRepositoryImpl(studentsListApi)
    }

    @Provides
    fun studentsListViewModelFactory(studentsListRepository: StudentsListRepository): StudentsListViewModelFactory {
        return StudentsListViewModelFactory(studentsListRepository)
    }

    @Provides
    fun studentsListApi(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): StudentsListApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL + "data/")
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(StudentsListApi::class.java)
    }
}