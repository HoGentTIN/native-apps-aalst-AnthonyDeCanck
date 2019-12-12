package com.example.androidproject.Network

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface DiscordMessageAPI {

    @GET("/users/@me/servers")
    fun getCurrentUserServers()

    @GET("/guilds/{serverid}/channels")
    fun getChannelsFromServer(@Path("serverid") serverid: Int)

    @POST("/channels/{channelid}/messages")
    fun postMessageToChannel(@Path("channelid") channelid: Int)

    fun authorizeUser()
}
