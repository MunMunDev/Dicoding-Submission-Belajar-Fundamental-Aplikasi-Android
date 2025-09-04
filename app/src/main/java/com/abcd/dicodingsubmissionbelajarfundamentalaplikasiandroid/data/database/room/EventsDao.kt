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
    suspend fun getAllFavoriteEvents(): List<ListEventsModel>

    @Query("SELECT COUNT(*) > 0 FROM events WHERE id = :id")
    suspend fun searchFavoriteEvents(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(events: ListEventsModel): Long

    @Delete
    suspend fun delete(events: ListEventsModel): Int
}