package io.github.kabirnayeem99.friends.data.viewobject

import com.google.gson.annotations.SerializedName

data class Dob(

    @SerializedName("date") val date: String,
    @SerializedName("age") val age: Int
)