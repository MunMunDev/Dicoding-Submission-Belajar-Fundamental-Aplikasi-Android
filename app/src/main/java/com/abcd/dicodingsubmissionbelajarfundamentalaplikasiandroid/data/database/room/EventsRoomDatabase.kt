package com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abcd.dicodingsubmissionbelajarfundamentalaplikasiandroid.data.modal.ListEventsModel

@Database(
    entities = [ListEventsModel::class],
    version = 1,
    exportSchema = false
)
abstract class EventsRoomDatabase : RoomDatabase() {
    abstract fun eventsDao(): EventsDao
    companion object {
        @Volatile
        private var INSTANCE: EventsRoomDatabase? = null
        @JvmStatic
        fun getDatabase(context: Context): EventsRoomDatabase {
            if (INSTANCE == null) {
                synchronized(EventsRoomDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        EventsRoomDatabase::class.java, "favorite_events_database")
                        .build()
                }
            }
            return INSTANCE as EventsRoomDatabase
        }
    }
}