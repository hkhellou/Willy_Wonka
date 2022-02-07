package com.example.willywonka.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Worker(
    @SerializedName("first_name") var firstName: String,
    @SerializedName("last_name") var lastName: String,
    @SerializedName("favorite") var favorite: Favorite,
    @SerializedName("gender") var gender: String,
    @SerializedName("image") var image: String,
    @SerializedName("profession") var profession: String,
    @SerializedName("email") var email: String,
    @SerializedName("age") var age: Int,
    @SerializedName("country") var country: String,
    @SerializedName("height") var height: Int,
    @SerializedName("id") var id: Int,
    @SerializedName("description") var description : String
) : Parcelable
