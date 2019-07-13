package com.example.nikumeshi_azddi9.roomusingtodolist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlin.concurrent.thread

class TaskViewModel (application: Application) : AndroidViewModel(application){
    private val db = TaskDatabase.getInstance(application).taskDao()
    private val _allTasks = db.getAllTasks()
    val allTasks = _allTasks

    fun insert(task: Task) = thread { db.insert(task) }
    fun update(task: Task) = thread { db.update(task) }
    fun logicalDelete(id: Int) = thread { db.logicalDelete(id) }
}