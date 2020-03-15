package com.ricknardo.mybelovedstreamers.ui.main_activity

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricknardo.mybelovedstreamers.data.remote.pojos.follows.DatumFollows
import com.ricknardo.mybelovedstreamers.data.remote.pojos.follows.UserFollowedStreams
import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.DatumStreams
import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.Pagination
import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.Streams
import com.ricknardo.mybelovedstreamers.data.remote.services.TwitchService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposables
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import kotlin.collections.HashSet

class MainActivityViewModel : ViewModel() {
    private val twitch = TwitchService.create()
    private var disposable = Disposables.empty()
    private val myTwitchStreamersOnline: MutableLiveData<List<DatumStreams>> =
        MutableLiveData(listOf())

    fun letsgo(login: String = "altairx12") {
        disposable = twitch.getUserInfo(login)
            .flatMap { letsGetAllFollows(it.data[0].id.toString(), "") }
            .flatMap { buildStreamersObservable(it.data) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.data.isNotEmpty()) {
                        myTwitchStreamersOnline.postValue(it.data)
                        Log.d("OMEGALOL", "the size is ${it.data.size}")
                    } else Log.d("OMEGALOL", "NO VE NA HULIO 0 CONECTEDXD")
                },
                { Log.d("OMEGAERROR", it.message) }
            )
    }

    private fun buildFollowsUrl(follows: List<DatumFollows>): List<String> {
        return follows.map {
            it.to_id.toString()
        }
    }

    private fun buildStreamersObservable(follows: List<DatumFollows>): Observable<Streams> {
        var ultimateObservable = Observable.just(
            Streams(
                listOf(),
                Pagination("")
            )
        )
        var mutableFollows = follows.toMutableList()

        for (i in 0..(follows.size / 100)) {
            if (mutableFollows.size >= 100) {
                val listToBeUsed = mutableFollows.subList(0, 100)
                ultimateObservable =
                    ultimateObservable
                        .zipWith(twitch.getStream(buildFollowsUrl(listToBeUsed.toList())),
                            BiFunction { t1: Streams, t2: Streams ->
                                val streamings: HashSet<DatumStreams> = HashSet()
                                for (x in t1.data) {
                                    streamings.add(x)
                                }
                                for (x in t2.data) {
                                    streamings.add(x)
                                }
                                t1.data = java.util.ArrayList(streamings)
                                t1
                            })
                mutableFollows = mutableFollows.minus(listToBeUsed).toMutableList()
            } else {
                ultimateObservable =
                    ultimateObservable
                        .zipWith(twitch.getStream(buildFollowsUrl(mutableFollows)),
                            BiFunction { t1: Streams, t2: Streams ->
                                val streamings: HashSet<DatumStreams> = HashSet()
                                for (x in t1.data) {
                                    streamings.add(x)
                                }
                                for (x in t2.data) {
                                    streamings.add(x)
                                }
                                t1.data = java.util.ArrayList(streamings)
                                t1
                            })
            }
        }

        return ultimateObservable
    }

    fun getMyStreamersOnline(): LiveData<List<DatumStreams>> {
        return myTwitchStreamersOnline
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