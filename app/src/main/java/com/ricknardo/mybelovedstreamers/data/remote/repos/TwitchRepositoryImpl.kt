package com.ricknardo.mybelovedstreamers.data.remote.repos

//import com.ricknardo.mybelovedstreamers.data.remote.pojos.follows.UserFollowedStreams
//import com.ricknardo.mybelovedstreamers.data.remote.pojos.streams.Streams
//import com.ricknardo.mybelovedstreamers.data.remote.pojos.userinfo.UserInfo
//import com.ricknardo.mybelovedstreamers.data.remote.services.TwitchService
//import io.reactivex.Observable
//
//class TwitchRepositoryImpl : TwitchRepository {
//    override fun getStream(users: Map<String, String>): Observable<Streams> {
//        TODO("Not yet implemented")
//    }
//
//    override fun getFollowedStreams(from_id: String?, after: String?): Observable<UserFollowedStreams> {
//        return TwitchService.create().getFollowedStreams(from_id, after)
//    }
//
//    override fun getUserInfo(login: String): Observable<UserInfo> {
//        return TwitchService.create().getUserInfo(login)
//    }
//}