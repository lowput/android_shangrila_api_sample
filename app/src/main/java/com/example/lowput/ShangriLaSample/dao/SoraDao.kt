package com.example.lowput.ShangriLaSample.dao

import com.example.lowput.ShangriLaSample.models.Cours
import com.example.lowput.ShangriLaSample.models.Sora
import com.example.lowput.ShangriLaSample.models.SoraList
import io.realm.Realm
import io.realm.RealmResults
import rx.android.schedulers.AndroidSchedulers
import rx.subscriptions.CompositeSubscription

/**
 * データアクセス
 * Created by lowput on 2016/11/20.
 */
class SoraDao {

    val subscription: CompositeSubscription = CompositeSubscription()

    private val soraClient = SoraClient()

    fun syncSoraData(cours: Cours,
                     setTitle: (cours: List<Sora>) -> Unit,
                     onError: (Unit) -> Unit) {
        val relmResult: RealmResults<SoraList> = Realm.getDefaultInstance().where(SoraList::class.java).equalTo("id", cours.id).findAll()
        if (relmResult.isEmpty()) {
            subscription.add(soraClient.master(cours)
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnNext {
                        list ->
                        Realm.getDefaultInstance().use { realm ->
                            realm.executeTransaction {
                                val soraList = realm.createObject(SoraList::class.java)
                                soraList.id = cours.id
                                list.forEach { soraList.data.add(it) }
                            }
                        }
                    }
                    .subscribe({
                        setTitle(it)
                    }, { onError })
            )
        } else {
            setTitle(relmResult.first().data)
        }
    }

    fun cours(): List<Cours> {
        return soraClient.cours.sortedBy(Cours::id)
    }

    fun onDestroy() = subscription.unsubscribe()
}
