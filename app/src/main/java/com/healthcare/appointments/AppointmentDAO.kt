package com.healthcare.appointments;

import androidx.lifecycle.LiveData;
import androidx.room.*


@Dao
@TypeConverters(DateTypeConverter::class)
interface AppointmentDAO {

    @Query("SELECT * from patient_appointment ORDER BY `appointmentDate ` ASC, id DESC")
    fun getAlphabetizedWords(): LiveData<List<Appointment>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(appointment: Appointment)

    @Query("DELETE FROM patient_appointment")
    suspend fun deleteAll()
}