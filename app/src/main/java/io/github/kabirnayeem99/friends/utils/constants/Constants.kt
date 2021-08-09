package io.github.kabirnayeem99.friends.utils.constants


/**
 * This class holds all the app level constants,
 * that won't change during the execution time of
 * this application
 */
object Constants {

    /**
     * Wi-Fi settings action name to open it directly with implicit intent
     */
    const val WIFI_SETTING_ACTION: String = "android.settings.WIFI_SETTINGS"

    /**
     * The base API URL to fetch the data from
     */
    const val BASE_URL: String = "https://randomuser.me/api/"

    /**
     * The amount of user that will be fetched from the API
     */
    const val RANDOM_USER_AMOUNT: Int = 10
}