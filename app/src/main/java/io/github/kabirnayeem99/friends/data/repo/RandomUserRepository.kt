package io.github.kabirnayeem99.friends.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.kabirnayeem99.friends.data.viewobject.Response
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.utils.Resource

class RandomUserRepository {

    fun getUserList(): LiveData<Resource<List<Response>>> {
        val userLiveData = MutableLiveData<Resource<List<Response>>>()

        return userLiveData
    }

    fun getUser(userId: String): User {
        return User()
    }

}