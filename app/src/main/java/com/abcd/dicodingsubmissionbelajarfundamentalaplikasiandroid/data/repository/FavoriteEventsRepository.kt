package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.repository

import android.app.Application
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room.EventsDao
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room.EventsRoomDatabase
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteEventsRepository @Inject constructor(
    application: Application
) {
    private val mEventsDao: EventsDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = EventsRoomDatabase.getDatabase(application)
        mEventsDao = db.eventsDao()
    }
    fun getAllEvents(): List<ListEventsModel> = mEventsDao.getAllEvents()

    fun insert(event: ListEventsModel) = executorService.execute { mEventsDao.insert(event) }

    fun delete(event: ListEventsModel) =  executorService.execute { mEventsDao.delete(event) }

    fun update(event: ListEventsModel) = executorService.execute { mEventsDao.update(event) }

}