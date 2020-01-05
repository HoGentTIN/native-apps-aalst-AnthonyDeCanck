package com.example.androidproject

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.androidproject.database.MessageDao
import com.example.androidproject.database.MessageDatabase
import com.example.androidproject.domain.Message
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.util.Date

class DatabaseTest {

    private lateinit var dao: MessageDao
    private lateinit var db: MessageDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory wordDao because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, MessageDatabase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        dao = db.messageDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWord() {
        // We have to specify the ID here or we can't test for object equality in assert due to
        // autoGeneration of the ID
        val message = Message(100,"Title","Author","Body", Date())
        runBlocking{
            dao.insert(message)
            val allMessages = dao.getMessages()
            assertTrue(allMessages.contains(message))
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGet1Word() {
        // We have to specify the ID here or we can't test for object equality in assert due to
        // autoGeneration of the ID
        val message = Message(100,"Title","Author","Body", Date())
        runBlocking{
            dao.insert(message)
            val message = dao.getMessage(100)
            assertTrue(message.id == 100)
        }
    }

}