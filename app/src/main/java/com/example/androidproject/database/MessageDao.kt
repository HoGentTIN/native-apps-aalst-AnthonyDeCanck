package com.example.androidproject.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidproject.domain.Message

@Dao
interface MessageDao {

    @Query("SELECT * FROM message_table")
    fun getMessages(): List<Message>

    // When not specified Room will crash when trying to enter a Word twice.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(message: Message)

    @Query("SELECT * FROM message_table WHERE id = :messId")
    fun getMessage(messId: Int): Message
}
