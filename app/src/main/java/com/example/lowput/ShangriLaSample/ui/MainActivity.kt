package com.example.lowput.ShangriLaSample.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.lowput.ShangriLaSample.R
import com.example.lowput.ShangriLaSample.databinding.ActivityMainBinding
import com.example.lowput.ShangriLaSample.ui.viewmodels.SoraViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //binding
        val bind: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val soraViewModel = SoraViewModel()

        bind.spinner.adapter = CoursAdapter(this, soraViewModel.getCoursList())

        soraViewModel.getTitleList("2016", "4")
                .subscribe(
                        { list ->
                            val adapter = TitleListAdapter(this)
                            adapter.addAll(list)
                            bind.listView.adapter = adapter
                        })
    }
}
