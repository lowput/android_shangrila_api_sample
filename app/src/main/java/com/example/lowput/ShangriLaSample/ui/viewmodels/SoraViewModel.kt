package com.example.lowput.ShangriLaSample.ui.viewmodels

import com.example.lowput.ShangriLaSample.dao.SoraAPI
import com.example.lowput.ShangriLaSample.dao.SoraClient
import com.example.lowput.ShangriLaSample.dao.SoraDao
import com.example.lowput.ShangriLaSample.models.Cours
import com.example.lowput.ShangriLaSample.models.Sora
import rx.Observable

/**
 * アニメタイトルViewModel
 * Created by lowput on 2016/11/05.
 */
class SoraViewModel {
    private val sora = SoraDao()

    fun getMasterList(cours: Cours): Observable<List<Sora>> =
            sora.syncSoraData(cours)

    fun getCoursList(): List<Cours> {
        return sora.cours()
    }

    fun isCoursEmpty(): Boolean {
        return sora.cours().isEmpty()
    }
}
