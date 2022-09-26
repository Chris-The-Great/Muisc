package com.example.ccmusichomework.model


import com.example.ccmusichomework.model.SongData
import com.google.gson.annotations.SerializedName

data class MusicNetworkData(
    @SerializedName("resultCount")
    val resultCount: Int? = null,
    @SerializedName("results")
    val songData: List<SongData?>? = null
)