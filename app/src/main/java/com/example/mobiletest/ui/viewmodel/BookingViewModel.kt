package com.example.mobiletest.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mobiletest.service.BookingDataManager
import com.example.mobiletest.service.model.Segment

class BookingViewModel : ViewModel() {
    private var bookingLiveData = MutableLiveData<List<Segment>>()
    fun getBookingData(forceRefresh: Boolean = false) {
        BookingDataManager.fetchBookingData(
            forceRefresh = forceRefresh, onSuccess = { data ->
                run { bookingLiveData.value = data.segments }
            },
            onFail = {})
    }

    fun observeBookingLiveData() = bookingLiveData
}