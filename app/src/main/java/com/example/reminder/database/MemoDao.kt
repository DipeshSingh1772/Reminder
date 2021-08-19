package com.example.reminder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMemo(memo:Memo)

    @Query(value = "SELECT * FROM memo_table order by id")
    fun getAllMemo():LiveData<List<Memo>>
}