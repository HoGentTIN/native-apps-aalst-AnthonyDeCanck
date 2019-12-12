package com.example.androidproject.Network

interface DiscordMessageAPI {

    fun getServersFromUser(userid: Int)
    fun getChannelsFromServer(serverid: Int)
    fun postMessageToChannel()

    fun getOath2()

    fun authorizeUser()
}
