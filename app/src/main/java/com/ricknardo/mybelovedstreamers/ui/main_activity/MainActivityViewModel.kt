package com.ricknardo.mybelovedstreamers.ui.main_activity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.internal.Streams
import com.ricknardo.mybelovedstreamers.data.remote.pojos.follows.DatumFollows
import com.ricknardo.mybelovedstreamers.data.remote.pojos.follows.UserFollowedStreams
import com.ricknardo.mybelovedstreamers.data.remote.services.TwitchService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlin.collections.HashSet

class MainActivityViewModel : ViewModel() {
    private val twitch = TwitchService.create()
    private var disposable = Disposables.empty()
    val user: MutableLiveData<Streams> = MutableLiveData()

    fun letsgo(login: String = "altairx12") {
        disposable = twitch.getUserInfo(login)
            .flatMap { letsGetAllFollows(it.data[0].id.toString(), "") }
            .flatMapIterable { it.data }
            .flatMap { twitch.getStream(it.to_id.toString()) }
            .filter { it.data.isNotEmpty() }
            .toList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    for (lol in it) {
                        for (x in lol.data) {
                            Log.d("OMEGALOL", x.user_name)
                        }
                    }
                },
                { Log.d("OMEGAERROR", it.message) }
            )
    }

    private fun letsGetAllFollows(from_id: String, after: String): Observable<UserFollowedStreams> {
        return twitch.getFollowedStreams(from_id, after)
            .concatMap { t: UserFollowedStreams ->
                if (t.pagination.cursor == null) {
                    twitch.getFollowedStreams(from_id, after)
                } else {
                    Observable.zip(
                        twitch.getFollowedStreams(from_id, after),
                        letsGetAllFollows(from_id, t.pagination.cursor),
                        BiFunction { t1: UserFollowedStreams, t2: UserFollowedStreams ->
                            val follows: HashSet<DatumFollows> = HashSet()
                            for (x in t1.data) {
                                follows.add(x)
                            }
                            for (x in t2.data) {
                                follows.add(x)
                            }
                            t1.data = java.util.ArrayList(follows)
                            t1
                        })
                }
            }
    }
}