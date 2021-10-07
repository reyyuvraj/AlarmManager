package com.example.alarmmanager.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "alarm_table")
data class Alarm (
    @PrimaryKey(autoGenerate = true)
    val id: Int
) : Parcelable