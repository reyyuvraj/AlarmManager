package com.example.alarmmanager.repository

import androidx.lifecycle.LiveData
import com.example.alarmmanager.data.AlarmDao
import com.example.alarmmanager.model.Alarm

class Repository constructor(private val alarmDao: AlarmDao) {

    val readAllData: LiveData<List<Alarm>> = alarmDao.readAllData()
    val alarmId: LiveData<Int> = alarmDao.alarmId()

    suspend fun addAlarm(alarm: Alarm) {
        alarmDao.addAlarm(alarm)
    }

    suspend fun deleteAlarm(alarm: Alarm){
        alarmDao.deleteAlarm(alarm)
    }

    suspend fun updateAlarm(alarm: Alarm){
        alarmDao.updateAlarm(alarm)
    }
}