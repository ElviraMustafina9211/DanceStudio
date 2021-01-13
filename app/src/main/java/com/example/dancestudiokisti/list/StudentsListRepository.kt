package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.Student
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StudentsListRepository {
    private val studentsListApi: StudentsListApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/BC19FC06-5B9B-D587-FF97-395A88AA5A00/56C210DD-8C5F-4AA8-95A4-27CA82766F09/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        studentsListApi = retrofit.create(StudentsListApi::class.java)
    }

    fun getStudentList(): Single<List<Student>> {
        return studentsListApi.getStudentsList()
    }
}