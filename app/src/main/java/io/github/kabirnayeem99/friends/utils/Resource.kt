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
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    /**
     * Signifies the successful state,
     * which may mean the data has been returned successfully
     *
     * takes the data (of [Any] type) as a parameter
     */
    class Success<T>(data: T) : Resource<T>(data)

    /**
     * Signifies the error state,
     * which means the data could not be loaded,
     * but there has been provided an error message
     * to inform about the issue.
     *
     * takes the error message ([String]) as a parameter
     */
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)

    /**
     * Signifies the loading state,
     * which means the data is not loaded yet,
     * it is currently processing
     */
    class Loading<T> : Resource<T>()
}