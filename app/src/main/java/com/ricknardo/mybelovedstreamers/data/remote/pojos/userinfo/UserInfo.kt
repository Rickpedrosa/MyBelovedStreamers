package com.ricknardo.mybelovedstreamers.data.remote.pojos.userinfo

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("data") val data: List<DatumUserInfo>
)
