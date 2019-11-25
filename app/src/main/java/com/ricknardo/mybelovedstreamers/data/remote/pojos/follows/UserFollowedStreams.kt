package com.ricknardo.mybelovedstreamers.data.remote.pojos.follows

import com.google.gson.annotations.SerializedName

data class UserFollowedStreams(
    @SerializedName("total") val total : Int,
    @SerializedName("data") var data : List<DatumFollows>,
    @SerializedName("pagination") val pagination : Pagination
)