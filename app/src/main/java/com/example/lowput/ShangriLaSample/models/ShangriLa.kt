package com.example.lowput.ShangriLaSample.models

/**
 * ShangriLa API ShangriLa データ定義
 * Created by lowput on 2016/11/03.
 */

const val ShangriLaURL: String = "http://api.moemoe.tokyo/"

data class Sora(
        val title_short2: String,
        val twitter_account: String,
        val public_url: String,
        val title_short1: String,
        val sex: Int,
        val twitter_hash_tag: String,
        val id: Int,
        val sequel: Int,
        val created_at: String,
        val cours_id: String,
        val title: String,
        val title_short3: String,
        val updated_at: String)
