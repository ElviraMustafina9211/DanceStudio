package com.example.dancestudiokisti.newStudent

import com.example.dancestudiokisti.Student
import com.example.dancestudiokisti.list.StudentsListApi
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NewStudentRepository {
    private val studentsListApi: StudentsListApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/BFE4B2B4-C4D0-488E-98CC-31B43E75F466/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        studentsListApi = retrofit.create(StudentsListApi::class.java)
    }

    fun createStudent(student: Student): Single<Student> {
        return studentsListApi.createStudent(student)
    }
}