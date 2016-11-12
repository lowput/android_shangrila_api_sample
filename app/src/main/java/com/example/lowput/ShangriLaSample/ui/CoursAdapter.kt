package com.example.lowput.ShangriLaSample.ui

import android.content.Context
import android.widget.ArrayAdapter
import com.example.lowput.ShangriLaSample.dao.Cours

/**
 * アニメ目次データ用Adapter
 *
 * Created by lowput on 2016/11/12.
 */
class CoursAdapter(context: Context, val coursList: List<Cours>) :
        ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item) {
    init {
        this.addAll(coursList.map { "${it.year}年${it.cours}クール" })
    }

    override fun getItemId(position: Int): Long {
        return coursList[position].id.toLong()
    }
}
