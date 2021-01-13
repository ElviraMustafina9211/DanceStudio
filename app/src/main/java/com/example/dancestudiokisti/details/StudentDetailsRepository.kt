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
            .baseUrl("https://api.backendless.com/BC19FC06-5B9B-D587-FF97-395A88AA5A00/56C210DD-8C5F-4AA8-95A4-27CA82766F09/data/")
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