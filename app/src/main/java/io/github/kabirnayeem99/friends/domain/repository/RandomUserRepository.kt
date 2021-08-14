package io.github.kabirnayeem99.friends.domain.repository

import androidx.lifecycle.MutableLiveData
import io.github.kabirnayeem99.friends.domain.model.User
import io.github.kabirnayeem99.friends.utils.Resource

/**
 * To make an interaction between the User Use cases and [RandomUserRepositoryImpl]
 */
interface RandomUserRepository {

    /**
     * Gets the User's list from the REST API
     */
    fun getUserList(userAmount: Int): MutableLiveData<Resource<List<User>>>
}