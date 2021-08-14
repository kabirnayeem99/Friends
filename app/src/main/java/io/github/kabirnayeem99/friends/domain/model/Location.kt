package io.github.kabirnayeem99.friends.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Location(

    @SerializedName("street") val street: Street,
    @SerializedName("city") val city: String,
    @SerializedName("state") val state: String,
    @SerializedName("country") val country: String,
) : Parcelable