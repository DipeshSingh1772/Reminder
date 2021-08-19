package com.example.reminder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memo_table")
data class Memo(

    @ColumnInfo(name = "Memo")
    val memo:String,

){
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0

    @ColumnInfo(name = "Status")
    val status:Boolean = false

}
