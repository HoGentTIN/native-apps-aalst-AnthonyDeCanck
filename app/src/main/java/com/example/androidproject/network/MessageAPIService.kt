package com.example.androidproject.network

import com.example.androidproject.domain.Message
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.text.SimpleDateFormat
import java.util.Date
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

const val BASE_URL = "https://my-json-server.typicode.com/AnthonyDeCanck/AndroidBackendJson/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .add(DateAdapter)
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface MessageAPIService {

    @GET("messages")
    suspend fun getMessages(): List<Message>

    @GET("messages/{id}")
    suspend fun getMessage(@Path("id") id: Int): Message
}

object MessageApi {
    val retrofitService: MessageAPIService by lazy {
        retrofit.create(MessageAPIService::class.java)
    }
}

object DateAdapter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    @FromJson
    fun fromJson(reader: JsonReader): Date? {
        return try {
            val dateAsString = reader.nextString()
            dateFormat.parse(dateAsString)
        } catch (e: Exception) {
            null
        }
    }
    @ToJson
    fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(dateFormat.format(value))
        }
    }
}
