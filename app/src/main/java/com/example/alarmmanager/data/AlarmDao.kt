package com.example.alarmmanager.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.alarmmanager.model.Alarm

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlarm(alarm: Alarm)

    @Update
    suspend fun updateAlarm(alarm: Alarm)

    @Query("SELECT id FROM alarm_table ORDER BY id DESC LIMIT 1")
    fun alarmId(): LiveData<Int>

    @Delete
    suspend fun deleteAlarm(alarm: Alarm)

    @Query("SELECT * FROM alarm_table ORDER By id ASC")
    fun readAllData(): LiveData<List<Alarm>>
}