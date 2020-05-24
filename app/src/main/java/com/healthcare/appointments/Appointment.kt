package com.healthcare.appointments
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

import java.util.*

@Entity(tableName = "patient_appointment")
data class Appointment(
    @PrimaryKey(autoGenerate = true)  val id: Int? = null,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "birthDate ") val birthDate : Date,
    @ColumnInfo(name = "gender ") val gender : String,
    @ColumnInfo(name = "appointmentDate ") val appointmentDate : Date
)

