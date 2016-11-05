package com.example.lowput.ShangriLaSample.ui.viewmodels

import com.example.lowput.ShangriLaSample.dao.SoraDao

/**
 * アニメタイトルViewModel
 * Created by lowput on 2016/11/05.
 */
class SoraViewModel constructor() {
    fun getTitleList(year: String, course: String, success: (List<String>) -> Unit) {
        val sora = SoraDao()
        sora.getTitleList(year, course)
                .subscribe({
                    success(it)
                })
    }
}
