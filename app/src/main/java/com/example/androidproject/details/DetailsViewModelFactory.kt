package com.example.androidproject.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidproject.repository.MessageRepository

class DetailsViewModelFactory(private val messageRepository: MessageRepository, private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(messageRepository, id) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
