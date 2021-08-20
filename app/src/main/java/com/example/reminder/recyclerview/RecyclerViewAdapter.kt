package com.example.reminder.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.database.Memo

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ReminderAdapter>() {


    private val allMemoList = ArrayList<Memo>()

    inner class ReminderAdapter(itemView: View): RecyclerView.ViewHolder(itemView) {

        val reminderTextView: TextView = itemView.findViewById(R.id.reminder_text_adapter)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderAdapter {
        val viewHolder = ReminderAdapter(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_format,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ReminderAdapter, position: Int) {
        val positionOnScreen = allMemoList[position]
        holder.reminderTextView.text = positionOnScreen.memo
    }

    override fun getItemCount(): Int {
        return allMemoList.size
    }

    fun updateAll(itemList:List<Memo>){
        allMemoList.clear()
        allMemoList.addAll(itemList)

        notifyDataSetChanged()
    }

}