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

    fun getTitleList(year: String, course: String): Observable<List<String>> =
            sora.getMaster(year, course).map { it.map(Sora::title) }

    fun getCoursList(): List<Cours> {
        return sora.cours.sortedBy(Cours::id)
    }
}
