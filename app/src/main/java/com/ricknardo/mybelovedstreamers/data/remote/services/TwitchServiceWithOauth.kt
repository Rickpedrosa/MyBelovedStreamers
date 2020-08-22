package com.ricknardo.mybelovedstreamers.data.remote.services

import com.ricknardo.mybelovedstreamers.data.remote.repos.TwitchRepositoryOauth
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TwitchServiceWithOauth {
    companion object {
         private const val URL_BASE = "https://id.twitch.tv/oauth2/"
         const val CLIENT_ID = "823qra4glyf53cyelur66rmbq1ted8"
         const val SECRET_ID = "v29iqoz06f0pxn8itvlfyjr4ximhf7"

        fun create(): TwitchRepositoryOauth {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_BASE)
                .build()

            return retrofit.create(TwitchRepositoryOauth::class.java)
        }
    }
}