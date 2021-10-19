package com.example.alarmmanager.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.alarmmanager.R
import com.example.alarmmanager.model.Alarm

class AlarmAdapter(private val context: Context) : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    private var alarmList: List<Alarm> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_alarm,
                parent,
                false
            )

        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val single = alarmList[position]
        holder.hour.text = convertTime(single.hour.toString().toInt())
        holder.minutes.text = convertTime(single.minute.toString().toInt())
        holder.amPm.text = single.amPM
        holder.switch.isChecked = true

        holder.switch.setOnCheckedChangeListener { compoundButton, b ->  }

    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hour: TextView = itemView.findViewById(R.id.display_hour)
        val minutes: TextView = itemView.findViewById(R.id.display_minute)
        val amPm: TextView = itemView.findViewById(R.id.display_AmOrPm)
        val switch: SwitchCompat = itemView.findViewById(R.id.alarmSwitch)
    }

    private fun convertTime(input: Int): String {
        return if (input >= 10) {
            input.toString()
        } else {
            "0$input"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(alarm: List<Alarm>) {
        this.alarmList = alarm
        notifyDataSetChanged()
    }
}