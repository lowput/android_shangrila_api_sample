package com.example.lowput.ShangriLaSample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lowput.ShangriLaSample.R

/**
 * タイトルリストAdapter
 * Created by lowput on 2016/11/03.
 */
class TitleListAdapter(context: Context) : ArrayAdapter<String>(context, 0) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?): View {
        val layout = convertView ?: inflater.inflate(R.layout.parts_list_anime_title, parent, false)
        (layout.findViewById(R.id.anime_title) as TextView).text = getItem(position)
        return layout
    }
}
