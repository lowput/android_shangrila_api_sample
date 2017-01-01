package com.example.lowput.ShangriLaSample.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * ShangriLa API Sora データ定義
 * Created by lowput on 2016/11/03.
 */

const val ShangriLaURL: String = "http://api.moemoe.tokyo/"

data class Cours(
        val id: Int,
        val year: Int,
        val cours: Int)

@RealmClass
open class Sora(
        open var title_short2: String? = null,
        open var twitter_account: String? = null,
        open var public_url: String? = null,
        open var title_short1: String? = null,
        open var sex: Int = 0,
        open var twitter_hash_tag: String? = null,
        open var id: Int = 0,
        open var sequel: Int = 0,
        open var created_at: String? = null,
        open var cours_id: String? = null,
        open var title: String? = null,
        open var title_short3: String? = null,
        open var updated_at: String? = null) : RealmObject()

@RealmClass
open class SoraList(
        var data: RealmList<Sora> = RealmList(),
        @PrimaryKey var id: Int = 0) : RealmObject()