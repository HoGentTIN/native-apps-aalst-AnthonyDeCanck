package com.example.androidproject.details

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

class DetailsViewModel(private val messageRepository: MessageRepository, val messageId: Int) : ViewModel() {
    // TODO: Implement the ViewModel
    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _message: MutableLiveData<Message> = MutableLiveData()

    val message: LiveData<Message> get() = _message

    init {
        viewModelScope.launch {
            initialiseMessage()
        }
    }

    private suspend fun initialiseMessage() {
        uiScope.launch {
            _message.value = getMessageFromRepo()
        }
    }

    private suspend fun getMessageFromRepo(): Message {
        return withContext(Dispatchers.IO) {
            var message = messageRepository.getMessage(messageId)
            message
        }
    }
}
