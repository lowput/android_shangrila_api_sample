package com.example.lowput.ShangriLaSample.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lowput.ShangriLaSample.R
import com.example.lowput.ShangriLaSample.databinding.ActivityMainBinding
import com.example.lowput.ShangriLaSample.ui.viewmodels.SoraViewModel
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity() {

    val soraViewModel = SoraViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        val bind: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(bind.toolbar)

        //Realm init
        Realm.setDefaultConfiguration(RealmConfiguration.Builder(this).build())

        val titleListAdapter: TitleListAdapter = TitleListAdapter(this)
        val coursAdapter = CoursAdapter(this, soraViewModel.getCoursList(), {
            cours ->
            soraViewModel.getMasterList(this, cours, titleListAdapter)
        })

        bind.listView.adapter = titleListAdapter
        bind.spinner.adapter = coursAdapter
        bind.spinner.onItemSelectedListener = coursAdapter
    }

    override fun onDestroy() {
        soraViewModel.onDestroy()
        super.onDestroy()
    }
}
