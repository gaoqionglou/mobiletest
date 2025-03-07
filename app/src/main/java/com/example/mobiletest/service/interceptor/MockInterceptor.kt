package com.example.mobiletest.service.interceptor

import android.util.Log
import com.example.mobiletest.util.Util
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val url = request.url.toString()

        var mockData = if (url.contains("booking")) {
            Util.getMockJson("bookingData.json")
        } else {
            ""
        }
        val responseBuilder = Response.Builder()
            .code(200)
            .message("OK")
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(mockData.toResponseBody("application/json".toMediaTypeOrNull()))
            .addHeader("Content-Type", "application/json")
        Log.d("xxx", "mock data----------")
        return responseBuilder.build()
    }


}