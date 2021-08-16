package io.github.kabirnayeem99.friends.data.repository

import android.util.Log
import io.github.kabirnayeem99.friends.data.sources.RemoteDataSource
import io.github.kabirnayeem99.friends.domain.model.ApiResponse
import io.github.kabirnayeem99.friends.domain.model.User
import io.github.kabirnayeem99.friends.domain.repository.RandomUserRepository
import io.github.kabirnayeem99.friends.utils.Resource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class RandomUserRepositoryImpl @Inject constructor(private var remoteDataSource: RemoteDataSource) :
    RandomUserRepository {


    /**
     * Gets the User's list from the REST API
     */
    override fun getUserList(): Flowable<Resource<List<User>>> {

        return remoteDataSource.getResponse().map { apiResponse ->
            Resource.Success(apiResponse.userList)
        }

    }

    private val tag = "RandomUserRepositoryImp"
}