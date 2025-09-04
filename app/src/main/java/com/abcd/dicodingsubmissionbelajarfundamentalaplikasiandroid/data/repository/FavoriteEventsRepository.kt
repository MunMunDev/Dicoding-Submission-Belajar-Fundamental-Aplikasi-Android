package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository

import android.app.Application
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room.EventsDao
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room.EventsRoomDatabase
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteEventsRepository @Inject constructor(
    application: Application
) {
    private val mEventsDao: EventsDao

    init {
        val db = EventsRoomDatabase.getDatabase(application)
        mEventsDao = db.eventsDao()
    }
    suspend fun getAllEvents(): List<ListEventsModel> = mEventsDao.getAllEvents()

    suspend fun insert(event: ListEventsModel) = mEventsDao.insert(event)

    suspend fun delete(event: ListEventsModel) =  mEventsDao.delete(event)

}