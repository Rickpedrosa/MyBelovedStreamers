package com.ricknardo.mybelovedstreamers.data.remote.pojos.streams

import com.google.gson.annotations.SerializedName

data class DatumStreams (
    @SerializedName("id") val id : Long,
    @SerializedName("user_id") val user_id : Long,
    @SerializedName("user_name") val user_name : String,
    //todo por culpa de Amouranth game_id debe ser string en vez de long porque no le pone categoria
    //todo a su sleeping streaming =/
    @SerializedName("game_id") val game_id : String,
    @SerializedName("type") val type : String,
    @SerializedName("title") val title : String,
    @SerializedName("viewer_count") val viewer_count : Int,
    @SerializedName("started_at") val started_at : String,
    @SerializedName("language") val language : String,
    @SerializedName("thumbnail_url") val thumbnail_url : String,
    @SerializedName("tag_ids") val tag_ids : List<String>
)