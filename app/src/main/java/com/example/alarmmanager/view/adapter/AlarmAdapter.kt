package com.example.alarmmanager.view.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
        holder.hour.text = single.hour.toString()
        holder.minutes.text = single.minute.toString()
    }

    override fun getItemCount(): Int {
        return alarmList.size
    }

    inner class ViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hour: TextView = itemView.findViewById(R.id.display_hour)
        val minutes: TextView = itemView.findViewById(R.id.display_minute)
        val amPm: TextView = itemView.findViewById(R.id.display_AmOrPm)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(alarm: List<Alarm>) {
        this.alarmList = alarm
        notifyDataSetChanged()
    }
}