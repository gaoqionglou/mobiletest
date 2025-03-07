package com.example.mobiletest.service.repository

import com.example.mobiletest.MyApplication
import com.example.mobiletest.service.interceptor.MockInterceptor
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private val bookingApiClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(MockInterceptor())
            .cache(getCache())
            .build()
    }
    val bookingApi: BookingApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.booking.com/data/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(bookingApiClient)
            .build()
            .create(BookingApiService::class.java)
    }

    private fun getCache(): Cache = Cache(
        MyApplication.applicationContext().cacheDir,
        10 * 1024 * 1024
    )

}