package io.github.kabirnayeem99.friends.utils

import android.util.Log
import java.lang.Exception
import java.net.InetAddress

object Utilities {

    /**
     * Checks the internet connection of this app
     */
    fun isInternetAvailable(): Boolean {

        return try {
            val ipAddress: InetAddress = InetAddress.getByName("google.com")
            !ipAddress.equals("")
        } catch (e: Exception) {
            Log.e(TAG, "isInternetAvailable: $e")
            false
        }
    }

    private const val TAG = "Utilities"
}