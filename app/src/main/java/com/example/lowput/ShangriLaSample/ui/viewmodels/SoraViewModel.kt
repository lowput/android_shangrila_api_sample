package com.example.lowput.ShangriLaSample.ui.viewmodels

import com.example.lowput.ShangriLaSample.dao.SoraDao
import com.example.lowput.ShangriLaSample.models.Cours
import com.example.lowput.ShangriLaSample.models.Sora
import io.realm.Realm
import rx.Observable

/**
 * アニメタイトルViewModel
 * Created by lowput on 2016/11/05.
 */
class SoraViewModel(val realm: Realm) {

    private val sora = SoraDao(realm)

    fun getMasterList(cours: Cours): Observable<List<Sora>> =
            sora.syncSoraData(cours)

    fun getCoursList(): List<Cours> {
        return sora.cours()
    }

    fun isCoursEmpty(): Boolean {
        return sora.cours().isEmpty()
    }
}
