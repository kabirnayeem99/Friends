package io.github.kabirnayeem99.friends.data.viewobject

import com.google.gson.annotations.SerializedName

data class Registered(

    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)