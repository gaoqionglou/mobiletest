package com.example.mobiletest.service.repository

import com.example.mobiletest.service.model.BookingData
import retrofit2.Call
import retrofit2.http.GET


interface BookingApiService {
    @GET("getBookingData")
    fun getBookingData(): Call<BookingData>
}