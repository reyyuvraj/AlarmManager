package com.example.alarmmanager.view.fragment

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.alarmmanager.R
import com.example.alarmmanager.databinding.TimePickerFragmentBinding
import com.example.alarmmanager.receiver.AlarmReceiver
import com.example.alarmmanager.service.AlarmService
import java.text.DateFormat
import java.util.*

class TimePicker : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: TimePickerFragmentBinding
    private lateinit var alarmManager: AlarmManager
    private lateinit var alarmReceiver: AlarmReceiver

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
        alarmReceiver = AlarmReceiver()


        binding.start.setOnClickListener {
            Toast.makeText(requireContext(), "Button pressed", Toast.LENGTH_SHORT).show()
            alarmReceiver.startAlarm(requireContext())
        }

        binding.alarmRecycler.setOnClickListener {
            findNavController().navigate(R.id.action_timePicker_to_alarmList2)
        }

        return binding.root
    }

    private fun getTime() {
        val cal = Calendar.getInstance()

        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickTime() {
        binding.timePicker.setOnClickListener {
            getTime()
            TimePickerDialog(requireContext(), this, hour, minute, true).show()
        }
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        val cal = Calendar.getInstance()
        savedHour = p1
        savedMinute = p2

        cal.set(Calendar.HOUR_OF_DAY, savedHour)
        cal.set(Calendar.MINUTE, savedMinute)
        cal.set(Calendar.SECOND, 0)

        updateTimeText(cal)

        binding.textView.text = "$savedHour $savedMinute"
    }

    private fun updateTimeText(cal: Calendar) {
        var timeText = "Alarm set for : "
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(cal)

        binding.textView.text = timeText
    }

}