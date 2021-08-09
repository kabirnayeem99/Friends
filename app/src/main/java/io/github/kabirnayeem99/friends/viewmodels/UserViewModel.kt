package io.github.kabirnayeem99.friends.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.friends.data.repo.RandomUserRepository
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.utils.Resource
import io.github.kabirnayeem99.friends.utils.Utilities
import kotlinx.coroutines.*
import javax.inject.Inject


/**
 * ViewModel object to make SoC happen
 * It also helps to hold the data, when the
 * configuration of the app changes
 */
@HiltViewModel
class UserViewModel
@Inject constructor(var repo: RandomUserRepository) : ViewModel() {


    // viewmodel will hold the state of live data
    // so that the configuration change doesn't need
    // a data reload
    // reducing both user annoyance and api reload
    var userListLiveData: MutableLiveData<Resource<List<User>>>? = null

    var internetStatus: Boolean = true

    init {
        viewModelScope.launch {
            loadInternetStatus()
            fetchUserList()
        }
    }


    // loads the internet status
    private fun loadInternetStatus() {
        internetStatus = Utilities.isInternetAvailable()
    }

    // fetches the user list from the repository
    private fun fetchUserList() {
        userListLiveData = repo.getUserList(userAmount = 10)
    }


}