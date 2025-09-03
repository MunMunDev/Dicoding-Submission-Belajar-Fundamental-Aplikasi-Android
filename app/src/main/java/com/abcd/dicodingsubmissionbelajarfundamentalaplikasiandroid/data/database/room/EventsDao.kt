package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel

@Dao
interface EventsDao {
    fun getAllEvents(): LiveData<List<ListEventsModel>>
}