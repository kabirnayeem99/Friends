package io.github.kabirnayeem99.friends.data.services

import io.github.kabirnayeem99.friends.data.viewobject.ApiResponse
import io.github.kabirnayeem99.friends.utils.constants.Constants
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API service
 */
interface ApiService {

    @GET(".")
    fun getResponse(
        @Query("results") resultAmount: Int,
    ): Call<ApiResponse>
}