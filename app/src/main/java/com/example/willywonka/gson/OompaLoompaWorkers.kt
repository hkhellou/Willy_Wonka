package com.example.willywonka.gson

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OompaLoompaWorkers(
    @SerializedName("current") var currentWorker: Int,
    @SerializedName("total") var totalWorkers: Int,
    @SerializedName("results") var workersData: List<Worker>
) : Parcelable
