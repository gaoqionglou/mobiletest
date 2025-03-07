package com.example.mobiletest.service.cache

import android.content.Context
import com.example.mobiletest.MyApplication
import com.example.mobiletest.service.model.BookingData
import com.google.gson.Gson

object BookingCache {
    private val prefs = MyApplication.applicationContext()
        .getSharedPreferences("booking_cache", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveBookingData(data: BookingData) {
        prefs.edit()
            .putString("booking_data", gson.toJson(data))
            .putLong("last_update", System.currentTimeMillis())
            .putInt("duration", data.duration)
            .apply()
    }

    fun getCachedData(): BookingData? {
        val json = prefs.getString("booking_data", null)
        val lastUpdate = prefs.getLong("last_update", 0)
        val duration = prefs.getInt("duration", 0)
        return if (json != null && (System.currentTimeMillis() - lastUpdate) < duration) {
            gson.fromJson(json, BookingData::class.java)
        } else {
            null
        }
    }
}