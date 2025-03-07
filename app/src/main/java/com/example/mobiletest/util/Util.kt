package com.example.mobiletest.util

import com.example.mobiletest.MyApplication

object Util {
    fun getMockJson(fileName: String): String {
        val inputStream = MyApplication.applicationContext().assets.open(fileName)
        return inputStream.bufferedReader().use { it.readText() }
    }
}