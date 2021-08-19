package com.example.reminder.viewModel

import android.app.Application
import com.example.reminder.database.MemoDatabase

class ApplicationClass: Application() {

    val dataBase:MemoDatabase by lazy { MemoDatabase.getDatabase(this) }
}