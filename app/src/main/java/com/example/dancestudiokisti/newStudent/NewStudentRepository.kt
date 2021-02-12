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
            .baseUrl("https://api.backendless.com/FFE00D00-0065-AC54-FF29-8E6A4D2D4200/1E545F28-B2D8-43E5-9BF3-0E1E350F051D/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        studentsListApi = retrofit.create(StudentsListApi::class.java)
    }

    fun createStudent(student: Student): Single<Student> {
        return studentsListApi.createStudent(student)
    }
}