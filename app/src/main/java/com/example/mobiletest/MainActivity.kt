package com.example.mobiletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobiletest.databinding.MainBinding
import com.example.mobiletest.ui.adapter.BookingListAdapter
import com.example.mobiletest.ui.viewmodel.BookingViewModel

class MainActivity : ComponentActivity() {
    private lateinit var binding: MainBinding
    private lateinit var viewModel: BookingViewModel
    private lateinit var bookingListAdapter: BookingListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bookingListAdapter = BookingListAdapter()
        binding.bookingList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = bookingListAdapter
        }
        viewModel = ViewModelProvider(this).get(BookingViewModel::class.java)
        viewModel.getBookingData()
        viewModel.observeBookingLiveData().observe(this, Observer { data ->
            bookingListAdapter.setData(data)
        })
        binding.btnRefresh.setOnClickListener {
            viewModel.getBookingData()
        }
    }
}