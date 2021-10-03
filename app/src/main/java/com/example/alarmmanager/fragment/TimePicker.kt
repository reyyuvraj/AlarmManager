package com.example.alarmmanager.fragment

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import com.example.alarmmanager.databinding.TimePickerFragmentBinding
import java.util.*

class TimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: TimePickerFragmentBinding

    var hour = 0
    var minute = 0

    var savedHour = 0
    var savedMinute = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TimePickerFragmentBinding.inflate(layoutInflater)

        binding.timePicker

        return binding.root
    }


    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        savedHour = p1
        savedMinute = p2

        binding.textView.text = "$savedHour $savedMinute"
    }

    private fun getTime() {
        val cal = Calendar.getInstance()

        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }
}