package com.example.androidproject.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidproject.domain.Message
import com.example.androidproject.repository.MessageRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val messageRepository: MessageRepository) : ViewModel() {
    // TODO: Implement the ViewModel

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _messages: MutableLiveData<List<Message>> = MutableLiveData()

    val messages: LiveData<List<Message>> get() = _messages

    init {
        viewModelScope.launch {
            initialiseMessages()
        }
    }

    private suspend fun initialiseMessages() {
        uiScope.launch {
            _messages.value = getMessagesFromRepo()
        }
    }

    private suspend fun getMessagesFromRepo(): List<Message> {
        return withContext(Dispatchers.IO) {
            var messages = messageRepository.getMessages()
            messages
        }
    }
}
