package com.ricknardo.mybelovedstreamers.data.remote.pojos.userinfo

import com.google.gson.annotations.SerializedName

data class DatumUserInfo(
    @SerializedName("id") val id : Int,
    @SerializedName("login") val login : String,
    @SerializedName("display_name") val display_name : String,
    @SerializedName("type") val type : String,
    @SerializedName("broadcaster_type") val broadcaster_type : String,
    @SerializedName("description") val description : String,
    @SerializedName("profile_image_url") val profile_image_url : String,
    @SerializedName("offline_image_url") val offline_image_url : String,
    @SerializedName("view_count") val view_count : Int
)
