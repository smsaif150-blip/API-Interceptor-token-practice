package com.example.interceptorapitokenpractice2.networks

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(val token: String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer $token")
            .build()

        return chain.proceed(request)
    }


}