package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository

import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.api.ApiService
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ResponseModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ListEventsRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getResponseEvents(
        active: Int,
        search: String
    ): ResponseModel{
        return api.getResponseEvents(active, search)
    }

}