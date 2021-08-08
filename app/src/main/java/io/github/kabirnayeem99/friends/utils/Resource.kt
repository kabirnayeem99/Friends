package io.github.kabirnayeem99.friends.utils


/**
 * Generic Resource class
 * for more sophisticated status and
 * error handling
 *
 * This sealed class holds, three state,
 *
 * [Success], which takes the data as a parameter
 *
 * [Error], which takes error message [String] as a param
 *
 * [Loading], which takes no param
 *
 * [NoInternet], which also takes no param
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
}