package com.valuelab.photoapp.service

import retrofit2.Response
import java.io.IOException


abstract class NetworkApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            throw ApiException(
                response.body().toString()
            )
        }
    }

}

class ApiException(message: String) : IOException(message)