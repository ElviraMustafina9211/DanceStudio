package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.BuildConfig
import com.example.dancestudiokisti.list.StudentsListApi
import com.example.dancestudiokisti.sectionsList.SectionsListRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NewStudentModule {
    @Provides
    fun newStudentRepository(studentsListApi: StudentsListApi): NewStudentRepository {
        return NewStudentRepositoryImpl(studentsListApi)
    }

    @Provides
    fun newStudentViewModelFactory(
        newStudentRepository: NewStudentRepository,
        sectionsListRepository: SectionsListRepository
    ): NewStudentViewModelFactory {
        return NewStudentViewModelFactory(newStudentRepository, sectionsListRepository)
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