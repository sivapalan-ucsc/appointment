package com.healthcare.appointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import android.widget.Toast
import android.app.Activity
import android.content.Intent
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.LocalDate
import java.text.SimpleDateFormat
//import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appointmentViewModel: AppointmentViewModel
    private val newAppointmentActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = AppointmentListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        appointmentViewModel = ViewModelProvider(this).get(AppointmentViewModel::class.java)

        // Add an observer on the LiveData
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        appointmentViewModel.allAppointments.observe(this, Observer { appointments ->
            // Update the cached copy of the words in the adapter.
            appointments?.let { adapter.setWords(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewAppointmentActivity::class.java)
            startActivityForResult(intent, newAppointmentActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        AndroidThreeTen.init(this);
        var name = "";
        var appointment_date:Date = Date();
        var dob:Date = Date();
        var gender = "";
        val myFormat: String = "dd-MM-yyyy"
        if (requestCode == newAppointmentActivityRequestCode && resultCode == Activity.RESULT_OK) {

            data?.getStringExtra(NewAppointmentActivity.EXTRA_REPLY)?.let {
                name = it
            }
            data?.getStringExtra(NewAppointmentActivity.EXTRA_REPLY_APPOINTMENT_DATE)?.let {
                appointment_date =SimpleDateFormat(myFormat).parse(it)

            }
            data?.getStringExtra(NewAppointmentActivity.EXTRA_REPLY_DOB)?.let {
                dob = SimpleDateFormat(myFormat).parse(it)
            }
            data?.getStringExtra(NewAppointmentActivity.EXTRA_REPLY_GENDER)?.let {
                gender = it
            }
            val apointment = Appointment(name = name , birthDate = dob, gender = gender,  appointmentDate = appointment_date)
            appointmentViewModel.insert(apointment)
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
