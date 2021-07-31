package io.github.kabirnayeem99.friends.data.viewobject

import android.icu.text.IDNA
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("results") val results: List<User>,
    @SerializedName("info") val info: IDNA.Info
)
