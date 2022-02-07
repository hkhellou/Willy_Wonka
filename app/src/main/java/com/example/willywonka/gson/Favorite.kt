package com.example.willywonka.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(
    @SerializedName("color") var color: String,
    @SerializedName("food") var food: String,
    @SerializedName("random_string") var randomString: String,
    @SerializedName("song") var song: String,
) : Parcelable
