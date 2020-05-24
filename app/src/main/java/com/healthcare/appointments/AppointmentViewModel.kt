package com.healthcare.appointments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppointmentViewModel (application: Application) : AndroidViewModel(application) {

    private val repository: AppointmentRepository
    val allAppointments: LiveData<List<Appointment>>

    init {
        val appointmentsDao = AppointmentRoomDatabase.getDatabase(application, viewModelScope).appointmentDao()
        repository = AppointmentRepository(appointmentsDao)
        allAppointments = repository.allAppointment
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(appointment: Appointment) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(appointment)
    }
}