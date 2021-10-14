package com.example.alarmmanager.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.alarmmanager.data.AlarmDatabase
import com.example.alarmmanager.model.Alarm
import com.example.alarmmanager.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<Alarm>>
    private val repository: Repository

    init {
        val alarmDao = AlarmDatabase.getDatabase(application).alarmDao()
        repository = Repository(alarmDao)
        readAllData = repository.readAllData
    }

    fun addAlarm(alarm: Alarm) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAlarm(alarm)
        }
    }
}