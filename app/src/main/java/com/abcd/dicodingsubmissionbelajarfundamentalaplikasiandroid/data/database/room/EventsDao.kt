package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel

@Dao
interface EventsDao {
    @Query("SELECT * from events ORDER BY id ASC")
    suspend fun getAllEvents(): List<ListEventsModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(events: ListEventsModel)

    @Delete
    suspend fun delete(events: ListEventsModel)
}