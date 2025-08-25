package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("error")
    var error: Boolean? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("listEvents")
    var listEvents: List<ListEventsModel>? = null,
)

data class ListEventsModel (
    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("summary")
    var summary: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("imageLogo")
    var imageLogo: String? = null,

    @SerializedName("mediCover")
    var mediCover: String? = null,

    @SerializedName("category")
    var category: String? = null,

    @SerializedName("owerName")
    var owerName: String? = null,

    @SerializedName("cityName")
    var cityName: String? = null,

    @SerializedName("quota")
    var quota: String? = null,

    @SerializedName("registrants")
    var registrants: String? = null,

    @SerializedName("beginTime")
    var beginTime: String? = null,

    @SerializedName("endTime")
    var endTime: String? = null,

    @SerializedName("link")
    var link: String? = null,

)