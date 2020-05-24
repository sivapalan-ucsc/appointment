package com.healthcare.appointments

import androidx.lifecycle.LiveData


// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
    class AppointmentRepository(private val appointementDao: AppointmentDAO) {

        // Room executes all queries on a separate thread.
        // Observed LiveData will notify the observer when the data has changed.
        val allAppointment: LiveData<List<Appointment>> = appointementDao.getAlphabetizedWords()

        suspend fun insert(appointment: Appointment) {
            appointementDao.insert(appointment)
        }
    }