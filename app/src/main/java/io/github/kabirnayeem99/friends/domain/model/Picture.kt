package io.github.kabirnayeem99.friends.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Picture(

    @SerializedName("large") val large: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("thumbnail") val thumbnail: String
) : Parcelable
