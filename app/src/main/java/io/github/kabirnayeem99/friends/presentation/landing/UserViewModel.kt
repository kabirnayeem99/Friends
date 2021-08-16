package io.github.kabirnayeem99.friends.presentation.landing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.kabirnayeem99.friends.domain.model.User
import io.github.kabirnayeem99.friends.domain.repository.RandomUserRepository
import io.github.kabirnayeem99.friends.utils.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
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

    // to avoid modifiable state leaked to the UI
    private var userListFlowablePrivate: Flowable<Resource<List<User>>> =
        repo.getUserList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .cache()


    // to avoid modifiable state leaked to the UI
    private var internetStatusPrivate: MutableLiveData<Boolean> = MutableLiveData<Boolean>()


    /**
     * This is an observable which provides user data,
     * and notifies the UI when the state of the User list changes
     *
     * It returns a [LiveData] of [User] [List], wrapped with [Resource] sealed class
     *
     * Here, [Resource.Success] means that there is a list of [User] in the live data
     *
     * Here, [Resource.Error] means that there is no data, rather a error message to acknowledge
     * about the issue
     *
     * Here, [Resource.Loading] means that the data is still loading, and there is not error messge
     * or data in the stream
     *
     */
    var userListFlowable: Flowable<Resource<List<User>>> = userListFlowablePrivate


    /**
     * This is a lifecycle aware observable, which notifies the ui,
     * if the internet status of the application has been changed
     *
     * It provides [TRUE] value if the internet is connected,
     * or else returns [FALSE] value.
     */
    var internetStatus: LiveData<Boolean> = internetStatusPrivate


    private val tag = "UserViewModel"

}