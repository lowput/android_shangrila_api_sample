package com.example.lowput.ShangriLaSample.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.lowput.ShangriLaSample.R
import com.example.lowput.ShangriLaSample.databinding.ActivityMainBinding
import com.example.lowput.ShangriLaSample.ui.viewmodels.SoraViewModel
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        val bind: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        bind.toolbar.title = "Anime API"
        setSupportActionBar(bind.toolbar)

        Realm.setDefaultConfiguration(RealmConfiguration.Builder(this).build())
        val realm : Realm = Realm.getDefaultInstance()
        val soraViewModel = SoraViewModel(realm)

        if (soraViewModel.isCoursEmpty()) {
            onError()
            return
        }

        val adapter = TitleListAdapter(this)
        val coursAdapter = CoursAdapter(this, soraViewModel.getCoursList(), {
            cours ->
            soraViewModel.getMasterList(cours)
                    .subscribe({
                        list ->
                        adapter.clear()
                        adapter.addAll(list)
                    }, { onError() })
        })
        bind.listView.adapter = adapter
        bind.spinner.adapter = coursAdapter
        bind.spinner.onItemSelectedListener = coursAdapter
    }

    fun onError() {
        Toast.makeText(this, "取得エラー", Toast.LENGTH_LONG).show()
    }
}
