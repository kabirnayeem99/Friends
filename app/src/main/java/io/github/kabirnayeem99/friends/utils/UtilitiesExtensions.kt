package io.github.kabirnayeem99.friends.utils

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.github.kabirnayeem99.friends.BuildConfig
import io.reactivex.rxjava3.core.Single

fun Exception.print(tag: String, functionName: String) {
    if (BuildConfig.DEBUG) {
        Log.e(
            tag, "$functionName ${
                this.message ?: "An unknown error has occurred"
            }", this
        )

    }
}

fun <T> Single<T>.toLiveData(): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(this.toFlowable())
}