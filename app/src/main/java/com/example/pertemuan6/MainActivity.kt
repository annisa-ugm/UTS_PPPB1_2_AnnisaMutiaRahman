package com.example.pertemuan6

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker.OnDateChangedListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan6.databinding.ActivityMainBinding
import java.util.Calendar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding){
//            Get Array
            val monthList = resources.getStringArray(R.array.month)
//            val adapterSpinnerMonth = ArrayAdapter(this@MainActivity,
//                android.R.layout.simple_spinner_dropdown_item, monthList)
//            datepicker.month = adapterSpinnerMonth[]

//            Initiate
            var selectedTime ="${timePicker.hour}:${timePicker.minute}"
            val _tempCalendar : Calendar = Calendar.getInstance()
            _tempCalendar.timeInMillis = System.currentTimeMillis()
            var selectedDate = "${_tempCalendar.get(Calendar.DAY_OF_MONTH)} ${monthList[_tempCalendar.get(Calendar.MONTH)]} ${_tempCalendar.get(Calendar.YEAR)}"


//            Kehadiran Dropdown=======================================
            val kehadiranList = listOf("tepat waktu", "terlambat", "izin")
            val adapterKehadiran = ArrayAdapter(this@MainActivity,
                android.R.layout.simple_spinner_item, kehadiranList)
            adapterKehadiran.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            kehadiranSpinner.adapter = adapterKehadiran

            keteranganEdittext.visibility = View.GONE
//            Selected Kehadiran
            kehadiranSpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long) {
                        when (position) {
                            0 -> keteranganEdittext.visibility = View.GONE
                            1, 2 -> keteranganEdittext.visibility = View.VISIBLE
                        }

                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }
                }

            submitButton.setOnClickListener {
                selectedTime = "${timePicker.hour}:${timePicker.minute}"
                Toast.makeText(this@MainActivity, "Presensi berhasil $selectedDate jam $selectedTime" , Toast.LENGTH_SHORT).show()
            }



        }
    }
}