package com.example.reminder.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reminder.R
import com.example.reminder.database.Memo
import java.text.SimpleDateFormat

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.ReminderAdapter>() {


    private val allMemoList = ArrayList<Memo>()

    inner class ReminderAdapter(itemView: View): RecyclerView.ViewHolder(itemView) {

        val reminderTextView: TextView = itemView.findViewById(R.id.reminder_text_adapter)
        val timeAndDate:TextView = itemView.findViewById(R.id.time_on_recycle_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderAdapter {
        val viewHolder = ReminderAdapter(LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_format,parent,false))
        return viewHolder
    }

    override fun onBindViewHolder(holder: ReminderAdapter, position: Int) {
        val positionOnScreen = allMemoList[position]
        holder.reminderTextView.text = positionOnScreen.memo
        if(positionOnScreen.Hour == 0 && positionOnScreen.Date.toInt() != 0){
            holder.timeAndDate.text = SimpleDateFormat("EEEE, dd MMMM").format(positionOnScreen.Date)
        }
        else if(positionOnScreen.Hour != 0 && positionOnScreen.Date.toInt() != 0)
        {
            val valueofdate:String = SimpleDateFormat("EEEE, dd MMMM").format(positionOnScreen.Date)
            val valueoftime:String = positionOnScreen.Hour.toString()
            val valueofmin:String = positionOnScreen.Min.toString()
            holder.timeAndDate.text = ("$valueoftime:$valueofmin, $valueofdate")
        }
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