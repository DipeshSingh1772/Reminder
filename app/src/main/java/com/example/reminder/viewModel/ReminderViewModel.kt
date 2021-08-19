package com.example.reminder.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.reminder.database.Memo
import com.example.reminder.database.MemoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReminderViewModel(private val memoDao:MemoDao): ViewModel() {

    val allMemoList:LiveData<List<Memo>> = memoDao.getAllMemo()

    fun insertMemo(memoData:Memo) = viewModelScope.launch(Dispatchers.IO){
        memoDao.insertMemo(memoData)
    }

}

class ViewModelFactory(private val memoDao: MemoDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ReminderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ReminderViewModel(memoDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}