package com.example.androidproject.domain

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class DiscordMessageDataService {
    private val CLIENT_ID = "649577462245883904";
    private val CLIENT_SECRET = "rj4tCOT8ThCb7nImpTRlycsxFKIpoT1L";

    private val BASE_URL = "http://localhost:50451/api/discord/callback"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()
}