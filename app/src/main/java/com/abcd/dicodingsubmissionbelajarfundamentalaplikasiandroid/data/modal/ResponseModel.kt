package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal

import android.os.Parcel
import android.os.Parcelable
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

    @SerializedName("ownerName")
    var ownerName: String? = null,

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

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(summary)
        parcel.writeString(description)
        parcel.writeString(imageLogo)
        parcel.writeString(mediCover)
        parcel.writeString(category)
        parcel.writeString(ownerName)
        parcel.writeString(cityName)
        parcel.writeString(quota)
        parcel.writeString(registrants)
        parcel.writeString(beginTime)
        parcel.writeString(endTime)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ListEventsModel> {
        override fun createFromParcel(parcel: Parcel): ListEventsModel {
            return ListEventsModel(parcel)
        }

        override fun newArray(size: Int): Array<ListEventsModel?> {
            return arrayOfNulls(size)
        }
    }
}