package com.logmind.todolist.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ToDoDao {
    @Query("SELECT * FROM ToDoEntity")
    suspend fun getAll() : List<ToDoEntity>

    @Insert
    suspend fun insertTodo(todo: ToDoEntity)

    @Delete
    suspend fun deleteTodo(todo: ToDoEntity)
}