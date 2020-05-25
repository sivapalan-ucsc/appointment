package com.healthcare.appointments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.text.TextUtils
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class NewAppointmentActivity : AppCompatActivity() {

    private lateinit var editNameView: EditText
    private lateinit var editDobView: EditText
    private lateinit var editGenderView: EditText
    private lateinit var editAppointmentDateView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_appointment)
        editNameView = findViewById(R.id.edit_name)
        editDobView = findViewById(R.id.edit_dob)
        editGenderView = findViewById(R.id.edit_gender)
        editAppointmentDateView = findViewById(R.id.edit_appointment_date)
        val datePicker:DatePickerDialog = this.setDatePicker(editAppointmentDateView)
        datePicker.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        this.setDatePicker(editDobView)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editDobView.text) || TextUtils.isEmpty(editGenderView.text) || TextUtils.isEmpty(editAppointmentDateView.toString())) {
               // setResult(Activity.RESULT_CANCELED, replyIntent)
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
            } else {
                val name = editNameView.text.toString()
                val dob = editDobView.text.toString()
                val gender = editGenderView.text.toString()
                val appointmentDate = editAppointmentDateView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, name)
                replyIntent.putExtra(EXTRA_REPLY_DOB, dob)
                replyIntent.putExtra(EXTRA_REPLY_GENDER, gender)
                replyIntent.putExtra(EXTRA_REPLY_APPOINTMENT_DATE, appointmentDate)
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }

        }
    }
    private fun setDatePicker(dateEditText: EditText):DatePickerDialog {

        val myCalendar = Calendar.getInstance()

        val datePickerOnDataSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel(myCalendar, dateEditText)
        }
        val datePicker = DatePickerDialog(this@NewAppointmentActivity, datePickerOnDataSetListener, myCalendar
            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH))

        dateEditText.setOnClickListener {
                datePicker.show()
        }
        return datePicker
    }

    private fun updateLabel(myCalendar: Calendar, dateEditText: EditText) {
        val myFormat: String = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        dateEditText.setText(sdf.format(myCalendar.time))
    }

    companion object {
        const val EXTRA_REPLY = "com.healthcare.android.appointmentlistsql.REPLY"
        const val EXTRA_REPLY_DOB = "com.healthcare.android.appointmentlistsql.REPLY_DOB"
        const val EXTRA_REPLY_GENDER = "com.healthcare.android.appointmentlistsql.REPLY_GENDER"
        const val EXTRA_REPLY_APPOINTMENT_DATE = "com.healthcare.android.appointmentlistsql.REPLY_APPOINTMENT_DATE"
    }
}
