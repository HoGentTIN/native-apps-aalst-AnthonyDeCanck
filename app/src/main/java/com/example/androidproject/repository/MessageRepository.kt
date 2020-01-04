package com.example.androidproject.repository

import android.net.ConnectivityManager
import com.example.androidproject.database.MessageDao
import com.example.androidproject.domain.Message
import com.example.androidproject.network.MessageAPIService

class MessageRepository(
    private val messageDao: MessageDao,
    private val messageAPIService: MessageAPIService,
    private val cm: ConnectivityManager
) {
    suspend fun getMessages(): List<Message> {
        if (connectedToInternet()) {
            val onlineMessages = messageAPIService.getMessages()
            // Save locally for future offline requests
            saveInLocalDatabase(onlineMessages)
            return onlineMessages
        } else {
            return messageDao.getMessages()
        }
    }

    suspend fun getMessage(id: Int): Message {
        if (connectedToInternet()) {
            val onlineMessage = messageAPIService.getMessage(id)
            // Save locally for future offline requests
            saveOneInLocalDb(onlineMessage)
            return onlineMessage
        } else {
            return messageDao.getMessage(id)
        }
    }

    private fun saveInLocalDatabase(messages: List<Message>) {
        messages.forEach {
            messageDao.insert(it)
        }
    }

    private fun saveOneInLocalDb(message: Message) {
        messageDao.insert(message)
    }

    private fun connectedToInternet(): Boolean {
        return cm.activeNetwork != null
    }
}
