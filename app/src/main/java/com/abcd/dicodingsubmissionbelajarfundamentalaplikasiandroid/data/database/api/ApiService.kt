package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.api

import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/events")
    suspend fun getResponseEvents(
        @Query("active") active: Int,
        @Query("q") q: String,
    ): ResponseModel

}