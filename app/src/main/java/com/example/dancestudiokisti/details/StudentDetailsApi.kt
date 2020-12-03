package com.example.dancestudiokisti.details

import com.example.dancestudiokisti.Student
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface StudentDetailsApi {
    @GET("studentDetails/{objectId}")
    fun getStudentDetails(@Path("objectId") objectId: String): Single<Student>

    @PUT("studentDetails/{objectId}")
    fun updateStudent(@Path("objectId") objectId: String, @Body student: Student): Single<Student>

    @DELETE("studentDetails/{objectId}")
    fun deleteStudent(@Path("objectId") objectId: String): Completable
}