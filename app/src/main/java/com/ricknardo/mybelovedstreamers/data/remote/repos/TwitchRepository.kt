package com.ricknardo.mybelovedstreamers.data.remote.repos

import com.ricknardo.mybelovedstreamers.data.remote.pojos.follows.UserFollowedStreams
import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.Streams
import com.ricknardo.mybelovedstreamers.data.remote.pojos.userinfo.UserInfo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitchRepository {
    @GET("streams")
    fun getStream(@Query("user_id") user_id: String): Observable<Streams>

    @GET("users/follows")
    fun getFollowedStreams(
        @Query("from_id") from_id: String?,
        @Query("after") after: String? = ""
    ): Observable<UserFollowedStreams>

    @GET("users")
    fun getUserInfo(@Query("login") login: String): Observable<UserInfo>
}