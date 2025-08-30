package com.logmind.todolist.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(ToDoEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTodoDao() : ToDoDao
}