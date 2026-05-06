package com.example.interceptorapitokenpractice2.networks

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitClients {

    val BASE_URL = "https://dummyjson.com/"

    fun getClient(token: String?): Retrofit
    {
        val clientBuilder = OkHttpClient.Builder()

        if (!token.isNullOrEmpty())
        {
            clientBuilder.addInterceptor(AuthInterceptor(token))
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}