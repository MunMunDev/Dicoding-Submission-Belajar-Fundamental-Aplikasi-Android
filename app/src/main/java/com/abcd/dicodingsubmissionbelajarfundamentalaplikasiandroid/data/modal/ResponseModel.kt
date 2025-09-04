package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("error")
    var error: Boolean? = null,

    @SerializedName("message")
    var message: String? = null,

    @SerializedName("listEvents")
    var listEvents: List<ListEventsModel>? = null,
)

@Entity(tableName = "events")
data class ListEventsModel (
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null,

    @SerializedName("summary")
    @ColumnInfo(name = "summary")
    var summary: String? = null,

    @SerializedName("description")
    @ColumnInfo(name = "description")
    var description: String? = null,

    @SerializedName("imageLogo")
    @ColumnInfo(name = "imageLogo")
    var imageLogo: String? = null,

    @SerializedName("mediCover")
    @ColumnInfo(name = "mediCover")
    var mediCover: String? = null,

    @SerializedName("category")
    @ColumnInfo(name = "category")
    var category: String? = null,

    @SerializedName("ownerName")
    @ColumnInfo(name = "ownerName")
    var ownerName: String? = null,

    @SerializedName("cityName")
    @ColumnInfo(name = "cityName")
    var cityName: String? = null,

    @SerializedName("quota")
    @ColumnInfo(name = "quota")
    var quota: Int = 0,

    @SerializedName("registrants")
    @ColumnInfo(name = "registrants")
    var registrants: Int = 0,

    @SerializedName("beginTime")
    @ColumnInfo(name = "beginTime")
    var beginTime: String? = null,

    @SerializedName("endTime")
    @ColumnInfo(name = "endTime")
    var endTime: String? = null,

    @SerializedName("link")
    @ColumnInfo(name = "link")
    var link: String? = null,

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readValue(Int::class.java.classLoader) as Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(summary)
        parcel.writeString(description)
        parcel.writeString(imageLogo)
        parcel.writeString(mediCover)
        parcel.writeString(category)
        parcel.writeString(ownerName)
        parcel.writeString(cityName)
        parcel.writeValue(quota)
        parcel.writeValue(registrants)
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