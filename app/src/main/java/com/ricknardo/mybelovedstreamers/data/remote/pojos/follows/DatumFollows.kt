package com.ricknardo.mybelovedstreamers.data.remote.pojos.follows

import com.google.gson.annotations.SerializedName

data class DatumFollows(
    @SerializedName("from_id")
    val from_id: Long,
    @SerializedName("from_name")
    val from_name: String,
    @SerializedName("to_id")
    val to_id: Long,
    @SerializedName("to_name")
    val to_name: String,
    @SerializedName("followed_at")
    val followed_at: String
)
