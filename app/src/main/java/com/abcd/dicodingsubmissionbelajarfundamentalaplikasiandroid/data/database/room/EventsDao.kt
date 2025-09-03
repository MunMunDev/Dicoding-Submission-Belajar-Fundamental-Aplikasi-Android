package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel

@Dao
interface EventsDao {
    @Query("SELECT * from events ORDER BY id ASC")
    fun getAllEvents(): List<ListEventsModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(events: ListEventsModel)

    @Update
    fun update(events: ListEventsModel)

    @Delete
    fun delete(events: ListEventsModel)
}