package com.example.androidproject.Network

import retrofit2.http.GET

interface AuthorizationConnections {
    @GET()
    fun getOath2()
}