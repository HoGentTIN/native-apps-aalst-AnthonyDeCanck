package com.example.androidproject.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidproject.domain.Message
import com.example.androidproject.utils.TimeStringConverter

@Database(entities = [Message::class], version = 2, exportSchema = false)
@TypeConverters(TimeStringConverter::class)
abstract class MessageDatabase : RoomDatabase() {
    abstract val messageDao: MessageDao

    companion object {
        @Volatile
        private var INSTANCE: MessageDatabase? = null

        fun getInstance(context: Context): MessageDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MessageDatabase::class.java,
                        "message_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
