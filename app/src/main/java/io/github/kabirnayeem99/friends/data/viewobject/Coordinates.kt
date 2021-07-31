package io.github.kabirnayeem99.friends.data.viewobject

import com.google.gson.annotations.SerializedName

data class Coordinates(

    @SerializedName("latitude") val latitude: Double,
    @SerializedName("longitude") val longitude: Double
)