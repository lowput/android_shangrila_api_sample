package com.example.lowput.ShangriLaSample.ui.viewmodels

import android.content.Context
import android.widget.Toast
import com.example.lowput.ShangriLaSample.dao.SoraDao
import com.example.lowput.ShangriLaSample.models.Cours
import com.example.lowput.ShangriLaSample.ui.TitleListAdapter

/**
 * アニメタイトルViewModel
 * Created by lowput on 2016/11/05.
 */
class SoraViewModel {

    private val sora = SoraDao()

    fun getMasterList(context: Context, cours: Cours, titleListAdapter: TitleListAdapter) {
        sora.syncSoraData(cours,
                { list ->
                    titleListAdapter.clear()
                    titleListAdapter.addAll(list)
                },
                {
                    Toast.makeText(context, "取得エラー", Toast.LENGTH_LONG).show()
                })
    }

    fun getCoursList(): List<Cours> {
        return sora.cours()
    }

    fun onDestroy() = sora.onDestroy()
}
