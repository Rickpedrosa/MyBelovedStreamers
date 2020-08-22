package com.ricknardo.mybelovedstreamers.data.remote.repos

import com.ricknardo.mybelovedstreamers.data.remote.pojos.TokenResponse
import com.ricknardo.mybelovedstreamers.data.remote.services.TwitchServiceWithOauth
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

interface TwitchRepositoryOauth {
    @POST("token")
    fun getOauthTwitchToken(
        @Query("client_id") clientId: String = TwitchServiceWithOauth.CLIENT_ID,
        @Query("client_secret") clientSecret: String = TwitchServiceWithOauth.SECRET_ID,
        @Query("grant_type") grantType: String = "client_credentials"
    ): Observable<TokenResponse>
}