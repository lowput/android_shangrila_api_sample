package com.example.lowput.ShangriLaSample.ui.viewmodels

import com.example.lowput.ShangriLaSample.dao.Cours
import com.example.lowput.ShangriLaSample.dao.SoraDao
import com.example.lowput.ShangriLaSample.models.Sora
import rx.Observable

/**
 * アニメタイトルViewModel
 * Created by lowput on 2016/11/05.
 */
class SoraViewModel {
    private val sora = SoraDao()

    fun getMasterList(year: String, course: String): Observable<List<Sora>> =
            sora.master(year, course)

    fun getCoursList(): List<Cours> {
        return sora.cours.sortedBy(Cours::id)
    }

    fun isEmpty() = sora.cours.isEmpty()
}
