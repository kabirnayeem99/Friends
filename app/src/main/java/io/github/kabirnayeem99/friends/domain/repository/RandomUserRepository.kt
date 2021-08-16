package io.github.kabirnayeem99.friends.domain.repository

import io.github.kabirnayeem99.friends.domain.model.User
import io.github.kabirnayeem99.friends.utils.Resource
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

/**
 * To make an interaction between the User Use cases and [RandomUserRepositoryImpl]
 */
interface RandomUserRepository {

    /**
     * Gets the User's list from the REST API
     */
    fun getUserList(): Flowable<Resource<List<User>>>
    // i should not use android frameworks in the domain layer,
    // but as of now, i could not find any work around
}