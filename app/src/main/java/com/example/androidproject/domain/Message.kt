package com.example.androidproject.domain

data class Message(val messageBody: String, val destinations: List<MessageDestination>)
