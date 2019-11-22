package com.ricknardo.mybelovedstreamers.data.remote.services

import com.ricknardo.mybelovedstreamers.data.remote.repos.TwitchRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TwitchService {
    companion object {
        private const val URL_BASE = "https://api.twitch.tv/helix/"
        private const val CLIENT_ID = "823qra4glyf53cyelur66rmbq1ted8"

        fun create(): TwitchRepository {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL_BASE)
                .client(chainHeaders())
                .build()

            return retrofit.create(TwitchRepository::class.java)
        }

        private fun chainHeaders(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor {
                    it.proceed(
                        it.request().newBuilder().addHeader(
                            "Client-ID", CLIENT_ID
                        ).build()
                    )
                }.build()
        }
    }
}