package io.github.kabirnayeem99.friends.data.repository

import androidx.lifecycle.MutableLiveData
import io.github.kabirnayeem99.friends.data.sources.RemoteDataSource
import io.github.kabirnayeem99.friends.domain.model.ApiResponse
import io.github.kabirnayeem99.friends.domain.model.User
import io.github.kabirnayeem99.friends.domain.repository.RandomUserRepository
import io.github.kabirnayeem99.friends.utils.Resource
import io.github.kabirnayeem99.friends.utils.Utilities
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(private var remoteDataSource: RemoteDataSource) :
    RandomUserRepository {


    /**
     * Gets the User's list from the REST API
     */
    override fun getUserList(userAmount: Int): MutableLiveData<Resource<List<User>>> {
        val userLiveData = MutableLiveData<Resource<List<User>>>()


        // when the service starts, it takes time
        // and for this  time being, show a loading status
        if (Utilities.isInternetAvailable()) {
            userLiveData.postValue(Resource.Loading())
        }

        try {
            val call: Call<ApiResponse> = remoteDataSource.getResponse(userAmount)

            call.enqueue(object : Callback<ApiResponse> {
                override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {

                    val userList = getUserListOnResponse(response)
                    userLiveData.postValue(userList)
                }

                override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                    userLiveData.postValue(handleOnFailure(t))
                }
            })

        } catch (e: Exception) {
            handleOnFailure(e)
        }


        return userLiveData
    }

    private fun handleOnFailure(t: Throwable): Resource<List<User>> {
        return Resource.Error(
            t.message ?: "Could not load the data from API"
        )
    }


    private fun getUserListOnResponse(
        response: Response<ApiResponse>
    ): Resource<List<User>> {

        if (!response.isSuccessful) {
            return Resource.Error("Response was not successful")
        }

        if (response.code() != 200) {
            return Resource.Error("Response code was ${response.code()}")
        }

        if (response.body() == null) {
            return Resource.Error("Response body or [ApiResponse] was null.")
        }

        response.body()?.let { apiResponse ->
            return Resource.Success(apiResponse.userList)
        }

        return Resource.Error("Response was not successful")

    }

}