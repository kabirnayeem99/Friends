package io.github.kabirnayeem99.friends.data.viewobject

import com.google.gson.annotations.SerializedName

data class Timezone(

    @SerializedName("offset") val offset: String,
    @SerializedName("description") val description: String
)