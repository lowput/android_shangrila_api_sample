package com.example.lowput.ShangriLaSample.dao

import com.example.lowput.ShangriLaSample.models.ShangriLaURL
import com.example.lowput.ShangriLaSample.models.Sora
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * ShangriLa API ShangriLa クライアントインタフェース/Dao
 * Created by lowput on 2016/09/18.
 */
data class Cours(
        val id: Int,
        val year: Int,
        val cours: Int)

interface SoraClient {
    @GET("anime/v1/master/{year}/{course}")
    fun get(@Path("year") year: String, @Path("course") course: String): Observable<Array<Sora>>

    @GET("anime/v1/master/cours")
    fun cours(): Observable<Map<Int, Cours>>
}

class SoraDao() {
    val soraClient = Retrofit.Builder()
            .baseUrl(ShangriLaURL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build().create(SoraClient::class.java)

    fun getTitleList(year: String, course: String): Observable<List<String>> {
        return soraClient.get(year, course)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {list -> list.map(Sora::title)}
    }
}
