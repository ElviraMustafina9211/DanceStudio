package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class StudentDetailsModule {

    @Provides
    fun studentDetailsRepository(studentDetailsApi: StudentDetailsApi): StudentDetailsRepository {
        return StudentDetailsRepository(studentDetailsApi)
    }

    @Provides
    fun studentDetailsViewModelFactory(studentDetailsRepository: StudentDetailsRepository): StudentDetailsViewModelFactory {
        return StudentDetailsViewModelFactory(studentDetailsRepository)
    }

    @Provides
    fun studentDetailsApi(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory,
        okHttpClient: OkHttpClient
    ): StudentDetailsApi {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()
        return retrofit.create(StudentDetailsApi::class.java)
    }
}