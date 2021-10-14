package com.example.alarmmanager.view.fragment

import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmmanager.databinding.AlarmListBinding
import com.example.alarmmanager.model.Alarm
import com.example.alarmmanager.view.adapter.AlarmAdapter
import com.example.alarmmanager.viewmodel.ViewModel
import java.util.*

class AlarmList : Fragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: AlarmListBinding
    private lateinit var adapter: AlarmAdapter
    private lateinit var viewModel: ViewModel

    var hour = 0
    var minute = 0
    var timeOfDay = 0

    var savedHour = 0
    var savedMinute = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AlarmListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pickTime()

        val recyclerView: RecyclerView = binding.alarmList
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        adapter = AlarmAdapter(requireContext())
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.readAllData.observe(viewLifecycleOwner, {
            adapter.setData(it)
        })
    }


    //get instance to current time and date
    private fun getTime() {
        val cal = Calendar.getInstance()

        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
        timeOfDay = cal.get(Calendar.AM_PM)
    }

    //select time for the alarm
    private fun pickTime() {
        binding.addAlarm.setOnClickListener {
            getTime()
            Log.d("TAG", "onViewCreated: $hour\t$minute ")
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

        val alarm = Alarm(id = 0, hour = savedHour, minute = savedMinute)

        setTime(alarm)
    }

    private fun setTime(alarm: Alarm) {
        viewModel.addAlarm(alarm)
    }
}