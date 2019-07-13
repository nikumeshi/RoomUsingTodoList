package com.example.nikumeshi_azddi9.roomusingtodolist

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    @Query("select * from tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("select * from tasks where _id = :id")
    fun getTask(id: Int): Task

    @Insert
    fun insert(task: Task)
    @Update
    fun update(task: Task)
    @Delete
    fun delete(task: Task)

    @Query("update tasks set isEnable = 0 where _id = :id")
    fun logicalDelete(id: Int)

    @Query("delete from tasks")
    fun deleteAll()
}