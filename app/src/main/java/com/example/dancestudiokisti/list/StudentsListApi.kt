package com.example.dancestudiokisti.list

import com.example.dancestudiokisti.Student
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentsListApi {
    @GET("studentDetails?pageSize=100")
    fun getStudentsList(): Single<List<Student>>

    @POST("studentDetails")
    fun createStudent(@Body student: Student): Single<Student>


}