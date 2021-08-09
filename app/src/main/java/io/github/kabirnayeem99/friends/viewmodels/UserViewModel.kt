package io.github.kabirnayeem99.friends.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.friends.data.repo.RandomUserRepository
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.utils.Resource
import io.github.kabirnayeem99.friends.utils.Utilities
import io.github.kabirnayeem99.friends.utils.constants.Constants
import kotlinx.coroutines.*
import javax.inject.Inject


/**
 * ViewModel object that sits between the ui and back-end of the application
 *
 * which notifies the ui to change when there is a change in the back-end
 */
@HiltViewModel
class UserViewModel
@Inject constructor(private var repo: RandomUserRepository) : ViewModel() {


    // view model will hold the state of live data
    // so that the configuration change doesn't need
    // a data reload
    // reducing both user annoyance and api reload

    /**
     * user live data that provides user data list
     */
    var userListLiveData: MutableLiveData<Resource<List<User>>>? = null

    /**
     * acknowledges if the internet is turned on or off
     */
    var internetStatus: MutableLiveData<Boolean> = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            loadInternetStatus()
            fetchUserList()
        }
    }


    // loads the internet status
    private fun loadInternetStatus() {
        internetStatus.value = Utilities.isInternetAvailable()
    }

    // fetches the user list from the repository
    private fun fetchUserList() {
        userListLiveData = repo.getUserList(Constants.RANDOM_USER_AMOUNT)
    }

}