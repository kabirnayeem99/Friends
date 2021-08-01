package io.github.kabirnayeem99.friends.data.services

import io.github.kabirnayeem99.friends.data.viewobject.ApiResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * REST API service
 */
interface ApiService {

    @GET("?results=10")
    fun getResponse(): Call<ApiResponse>
}