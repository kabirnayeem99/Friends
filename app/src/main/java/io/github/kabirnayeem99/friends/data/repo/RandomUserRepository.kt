package io.github.kabirnayeem99.friends.data.repo

import androidx.lifecycle.MutableLiveData
import io.github.kabirnayeem99.friends.data.services.ApiService
import io.github.kabirnayeem99.friends.data.viewobject.ApiResponse
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.utils.Resource
import io.github.kabirnayeem99.friends.utils.Utilities
import io.github.kabirnayeem99.friends.utils.constants.Constants
import kotlinx.coroutines.CoroutineExceptionHandler
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RandomUserRepository @Inject constructor(var apiService: ApiService) {


    /**
     * Gets the User's list from the REST API
     */
    fun getUserList(userAmount: Int): MutableLiveData<Resource<List<User>>> {
        val userLiveData = MutableLiveData<Resource<List<User>>>()


        // when the service starts, it takes time
        // and for this  time being, show a loading status
        if (Utilities.isInternetAvailable()) {
            userLiveData.postValue(Resource.Loading())
        }

        try {
            val call: Call<ApiResponse> = apiService.getResponse(userAmount)

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
            return Resource.Error("Response code was no 200.")
        }

        if (response.body() == null) {
            return Resource.Error("Response body was null.")
        }

        response.body()?.let { apiResponse ->
            return Resource.Success(apiResponse.userList)
        }

        return Resource.Error("Response was not successful")

    }

}