package io.github.kabirnayeem99.friends.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.friends.data.repo.RandomUserRepository
import io.github.kabirnayeem99.friends.data.viewobject.User
import io.github.kabirnayeem99.friends.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * ViewModel object to make SoC happen
 * It also helps to hold the data, when the
 * configuration of the app changes
 */
@HiltViewModel
class UserViewModel @Inject constructor(var repo: RandomUserRepository) : ViewModel() {


    private var userListLiveData = MutableLiveData<Resource<List<User>>>()

    init {
        viewModelScope.launch {
            userListLiveData = repo.getUserList()
        }
    }

    /**
     * gets the list of the users from the repo
     */
    fun getUserList(): LiveData<Resource<List<User>>> = userListLiveData

}