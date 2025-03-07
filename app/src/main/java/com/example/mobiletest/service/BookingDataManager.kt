package com.example.mobiletest.service

import android.util.Log
import com.example.mobiletest.service.cache.BookingCache
import com.example.mobiletest.service.model.BookingData
import com.example.mobiletest.service.repository.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object BookingDataManager {
    fun fetchBookingData(
        forceRefresh: Boolean,
        onSuccess: (BookingData) -> Unit,
        onFail: () -> Unit
    ) {
        val cacheData = if (!forceRefresh) BookingCache.getCachedData() else null
        cacheData?.let {
            Log.d("xxx", "cache data loaded-------")
            onSuccess.invoke(cacheData)
            return
        }
        RetrofitClient.bookingApi.getBookingData().enqueue(object : Callback<BookingData> {
            override fun onResponse(call: Call<BookingData>, response: Response<BookingData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        BookingCache.saveBookingData(it)
                        onSuccess.invoke(it)
                    } ?: onFail.invoke()
                }
            }

            override fun onFailure(call: Call<BookingData>, t: Throwable) {
                onFail.invoke()
            }

        })
    }
}