package io.github.kabirnayeem99.friends.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    @SerializedName("name") val name: Name,
    @SerializedName("location") val location: Location,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("cell") val cell: String,
    @SerializedName("picture") val picture: Picture,
) : Parcelable