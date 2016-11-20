package com.example.lowput.ShangriLaSample.dao

import com.example.lowput.ShangriLaSample.models.Cours
import com.example.lowput.ShangriLaSample.models.Sora
import com.example.lowput.ShangriLaSample.models.SoraList
import io.realm.Realm
import io.realm.RealmResults
import rx.Observable

/**
 * Realm データアクセス
 * Created by lowput on 2016/11/20.
 */
class SoraDao() {
    private val soraClient = SoraClient()

    val realm: Realm = Realm.getDefaultInstance()

    fun syncSoraData(cours: Cours): Observable<List<Sora>> {
        val relmResult: RealmResults<SoraList> = realm.where(SoraList::class.java).equalTo("id", cours.id).findAll()
        if (relmResult.isEmpty()) {
            return soraClient.master(cours).doOnNext {
                list ->
                realm.executeTransaction {
                    val soraList = realm.createObject(SoraList::class.java)
                    soraList.id = cours.id
                    list.forEach { soraList.data.add(it) }
                }
            }
        } else {
            return relmResult.asObservable()
                    .filter { it.isLoaded }
                    .map { it.first().data }
        }
    }

    fun cours(): List<Cours> {
        return soraClient.cours.sortedBy(Cours::id)
    }
}
