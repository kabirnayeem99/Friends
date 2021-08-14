package io.github.kabirnayeem99.friends.data.sources

import io.github.kabirnayeem99.friends.domain.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * remove data source for the random friends list
 *
 * that will fetch the data from the API,
 *
 * in the [ApiResponse] format
 *
 * wrapped in a [Call] class
 */
interface RemoteDataSource {

    @GET(".")
    fun getResponse(
        @Query("results") resultAmount: Int,
    ): Call<ApiResponse>
}