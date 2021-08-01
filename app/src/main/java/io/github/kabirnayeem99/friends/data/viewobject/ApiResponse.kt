package io.github.kabirnayeem99.friends.data.viewobject

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("results") val userList: List<User>
)
