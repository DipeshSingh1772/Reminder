package com.example.reminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")
data class Memo(

    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,

    @ColumnInfo(name = "Memo")
    val memo:String,

    @ColumnInfo(name = "TD")
    val TimeDate:Long

){




}
