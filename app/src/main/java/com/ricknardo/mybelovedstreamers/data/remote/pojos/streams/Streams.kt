package com.ricknardo.mybelovedstreamers.data.remote.pojos.streams

import com.google.gson.annotations.SerializedName

data class Streams(
    @SerializedName("data") var data: List<DatumStreams>,
    @SerializedName("pagination") val pagination: Pagination
)