package com.example.nikumeshi_azddi9.roomusingtodolist

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase(){
    abstract fun taskDao(): TaskDao

    companion object{
        @Volatile
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase = instance ?: synchronized(this){
            Room.databaseBuilder(context, TaskDatabase::class.java, "roomSample.db").build()
        }
    }
}