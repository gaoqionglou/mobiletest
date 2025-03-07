package com.example.mobiletest.extension

import android.content.Context
import android.net.ConnectivityManager

private fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    return connectivityManager?.isDefaultNetworkActive == true
}