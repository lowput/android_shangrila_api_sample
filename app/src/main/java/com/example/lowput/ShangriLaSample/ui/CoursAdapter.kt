package com.example.lowput.ShangriLaSample.ui

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.lowput.ShangriLaSample.models.Cours

/**
 * アニメ目次データ用Adapter
 *
 * Created by lowput on 2016/11/12.
 */
class CoursAdapter(context: Context, val coursList: List<Cours>, val selectAction: (cours: Cours) -> Unit) :
        ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item), AdapterView.OnItemSelectedListener {
    init {
        this.addAll(coursList.map { "${it.year}年${it.cours}クール" })
    }

    override fun getItemId(position: Int): Long {
        return coursList[position].id.toLong()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        selectAction(coursList[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
