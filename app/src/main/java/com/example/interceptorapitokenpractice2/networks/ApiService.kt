package com.example.interceptorapitokenpractice2.networks

import com.example.interceptorapitokenpractice2.models.LoginRequest
import com.example.interceptorapitokenpractice2.models.LoginResponse
import com.example.interceptorapitokenpractice2.models.UserResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Body

interface ApiService {
    @POST("auth/login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("auth/me")
    fun getData():Call<UserResponse>
}