package com.example.dancestudiokisti

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor (private val tokenSaver: TokenSaver) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        tokenSaver.token?.let {
            val request = chain.request().newBuilder()
                .header("user-token", it)
                .build()
            return chain.proceed(request)
        } ?: run {
            return chain.proceed(chain.request())
        }
    }
}