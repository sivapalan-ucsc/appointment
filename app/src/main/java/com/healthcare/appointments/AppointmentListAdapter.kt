package com.healthcare.appointments

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class AppointmentListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<AppointmentListAdapter.AppointmentViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var appointements = emptyList<Appointment>() // Cached copy of words

    inner class AppointmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appointmentItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return AppointmentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppointmentViewHolder, position: Int) {
        val current = appointements[position]
        val myFormat: String = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        holder.appointmentItemView.text =  "#"+ current.id + "\nPatient Name : " + current.name + "\nDate of birth :" +sdf.format(current.birthDate.time) +"\nGender :" +current.gender + "\nAppointment date: "+sdf.format(current.appointmentDate.time)
    }

    internal fun setWords(appointments: List<Appointment>) {
        this.appointements = appointments
        notifyDataSetChanged()
    }

    override fun getItemCount() = appointements.size
}