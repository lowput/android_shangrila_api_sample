package com.example.lowput.ShangriLaSample.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.lowput.ShangriLaSample.R
import com.example.lowput.ShangriLaSample.models.Sora

/**
 * タイトルリストAdapter
 * Created by lowput on 2016/11/03.
 */
class TitleListAdapter(context: Context) : ArrayAdapter<Sora>(context, R.layout.parts_list_anime_title) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int,
                         convertView: View?,
                         parent: ViewGroup?): View {
        val layout = convertView ?: inflater.inflate(R.layout.parts_list_anime_title, parent, false)
        val textView = (layout.findViewById(R.id.anime_title) as TextView)

        // アニメタイトル
        val sora: Sora = getItem(position)
        textView.text = sora.title
        // 公式ホームページ
        val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(sora.public_url))
        layout.setOnClickListener { view -> context.startActivity(intent) }

        return layout
    }
}
