package io.github.kabirnayeem99.friends.utils

import android.util.Log
import java.lang.Exception
import java.net.InetAddress

/**
 * This class holds all the utilities function
 * that can be used throughout the application module
 */
object Utilities {

    /**
     * Checks the internet connection of this app
     *
     * Returns true if the app is connected to the internet
     *
     * Returns false if the app is not connected to the internet
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