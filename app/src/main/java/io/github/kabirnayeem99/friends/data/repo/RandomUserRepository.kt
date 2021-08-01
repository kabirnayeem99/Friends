package io.github.kabirnayeem99.friends.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.github.kabirnayeem99.friends.data.services.ApiService
import io.github.kabirnayeem99.friends.data.viewobject.ApiResponse
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RandomUserRepository @Inject constructor(var apiService: ApiService) {


    /**
     * Gets the User's list from the REST API
     */
    fun getUserList(): MutableLiveData<Resource<List<User>>> {
        val userLiveData = MutableLiveData<Resource<List<User>>>()

        // when the service starts, it takes time
        // and for this mean time, show a loading status
        userLiveData.postValue(Resource.Loading())

        val call = apiService.getResponse()

        call.enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {

                // with failure, assign the failure reasons to the resource error message
                userLiveData.postValue(Resource.Error(t.message ?: "Unknown error occurred"))
            }

            override fun onResponse(
                call: Call<ApiResponse>,
                response: Response<ApiResponse>
            ) {

                // with success, post the data to the live data
                val data: ApiResponse? = response.body()

                if (data != null) {
                    userLiveData.postValue(Resource.Success(data.userList))
                }
            }
        })

        return userLiveData
    }

}