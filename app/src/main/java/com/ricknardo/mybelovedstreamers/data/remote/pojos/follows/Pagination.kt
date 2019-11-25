package com.ricknardo.mybelovedstreamers.data.remote.pojos.follows

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("cursor") val cursor: String?
)
