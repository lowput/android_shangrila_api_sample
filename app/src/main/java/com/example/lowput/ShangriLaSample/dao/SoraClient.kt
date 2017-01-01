package com.example.lowput.ShangriLaSample.dao

import com.example.lowput.ShangriLaSample.models.Cours
import com.example.lowput.ShangriLaSample.models.ShangriLaURL
import com.example.lowput.ShangriLaSample.models.Sora
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable
import rx.schedulers.Schedulers

/**
 * ShangriLa API クライアントインタフェース/Dao
 * Created by lowput on 2016/09/18.
 */

interface SoraAPI {
    @GET("anime/v1/master/{year}/{course}")
    fun get(@Path("year") year: String, @Path("course") course: String): Observable<Array<Sora>>

    @GET("anime/v1/master/cours")
    fun cours(): Observable<Map<Int, Cours>>
}

class SoraClient {
    val soraClient = Retrofit.Builder()
            .baseUrl(ShangriLaURL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build().create(SoraAPI::class.java)

    val cours: List<Cours> = soraClient.cours()
            .subscribeOn(Schedulers.io())
            .map { it.map(Map.Entry<Int, Cours>::value) }
            .onErrorReturn({ throwable -> emptyList() })
            .toBlocking()
            .first()

    fun master(cours: Cours): Observable<List<Sora>> {
        return soraClient.get(cours.year.toString(), cours.cours.toString())
                .subscribeOn(Schedulers.io())
                .map { it.toList() }
    }
}
