package com.example.androidproject.Network

import android.content.Intent
import android.net.Uri
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class DiscordMessageDataService : DiscordMessageAPI,AuthorizationConnections {

    private val CLIENT_ID = "649577462245883904"
    private val CLIENT_SECRET = "rj4tCOT8ThCb7nImpTRlycsxFKIpoT1L"

    private val BASE_URL = "http://localhost:50451/api/discord/callback"

    private val OAUTH_REQUEST = "https://discordapp.com/api/oauth2/" +
            "authorize?client_id=649577462245883904" +
            "&redirect_uri=http%3A%2F%2Flocalhost%3A50451%2Fapi%2Fdiscord%2Fcallback" +
            "&response_type=code" +
            "&scope=identify%20guilds"

    private val retrofitOAUTH = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(OAUTH_REQUEST)
        .build()

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    override fun getCurrentUserServers() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChannelsFromServer(serverid: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postMessageToChannel(channelid: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getOath2() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(OAUTH_REQUEST) )
        //TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    override fun authorizeUser() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }
}
