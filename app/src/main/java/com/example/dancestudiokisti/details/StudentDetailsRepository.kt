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
            .baseUrl("https://api.backendless.com/FFE00D00-0065-AC54-FF29-8E6A4D2D4200/1E545F28-B2D8-43E5-9BF3-0E1E350F051D/data/")
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