package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.Student
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class StudentDetailsRepository {

    private val studentsDetailsApi: StudentDetailsApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.backendless.com/5DB01662-49AB-46C2-FF61-D1BE9E31E700/BFE4B2B4-C4D0-488E-98CC-31B43E75F466/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        studentsDetailsApi = retrofit.create(StudentDetailsApi::class.java)
    }

    fun getStudentDetails(objectId: String): Single<Student> {
        return studentsDetailsApi.getStudentDetails(objectId)
    }

    fun updateStudentDetails(objectId: String, student: Student): Single<Student> {
        return studentsDetailsApi.updateStudent(objectId, student)
    }
    fun deleteStudentDetails(objectId: String): Completable {
        return studentsDetailsApi.deleteStudent(objectId)
    }
}