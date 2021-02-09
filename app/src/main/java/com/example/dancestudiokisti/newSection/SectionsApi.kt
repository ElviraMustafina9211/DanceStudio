package com.example.dancestudiokisti.newSection

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface SectionsApi {

    @GET("sections?pageSize=100")
    fun getSectionsList(@Header("user-token") userToken: String): Single<List<Section>>

    @POST("sections")
    fun createSection(@Body section: Section): Single<Section>

    @DELETE("sections/{objectId}")
    fun deleteOneSection(@Path("objectId") objectId: String): Completable
}