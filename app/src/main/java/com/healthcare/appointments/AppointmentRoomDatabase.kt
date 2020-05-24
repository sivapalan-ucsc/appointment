package com.healthcare.appointments

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kotlinx.coroutines.CoroutineScope

// Annotates class to be a Room Database with a table (entity) of the Appointment class
@Database(entities = arrayOf(Appointment::class), version = 1, exportSchema = false)
@TypeConverters(DateTypeConverter::class)
public abstract class AppointmentRoomDatabase : RoomDatabase() {

    abstract fun appointmentDao(): AppointmentDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppointmentRoomDatabase? = null

        fun getDatabase(context: Context,scope: CoroutineScope): AppointmentRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppointmentRoomDatabase::class.java,
                    "appointement_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }



    }
}