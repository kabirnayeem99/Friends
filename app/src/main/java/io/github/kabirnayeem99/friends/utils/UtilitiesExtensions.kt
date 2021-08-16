package io.github.kabirnayeem99.friends.utils

import android.util.Log
import io.github.kabirnayeem99.friends.BuildConfig

fun Exception.print(tag: String, functionName: String) {
    if (BuildConfig.DEBUG) {
        Log.e(
            tag, "$functionName: ${
                this.message ?: "An unknown error has occurred"
            }", this
        )

    }
}

