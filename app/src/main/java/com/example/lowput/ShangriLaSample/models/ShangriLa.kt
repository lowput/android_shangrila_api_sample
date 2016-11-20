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

@RealmClass
open class Cours(
        var id: Int,
        var year: Int,
        var cours: Int) : RealmObject()

@RealmClass
open class Sora(
        var title_short2: String? = null,
        var twitter_account: String? = null,
        var public_url: String? = null,
        var title_short1: String? = null,
        var sex: Int = 0,
        var twitter_hash_tag: String? = null,
        var id: Int = 0,
        var sequel: Int = 0,
        var created_at: String? = null,
        var cours_id: String? = null,
        var title: String? = null,
        var title_short3: String? = null,
        var updated_at: String? = null) : RealmObject()

@RealmClass
open class SoraList(
        var data: RealmList<Sora> = RealmList(),
        @PrimaryKey var id: Int = 0) : RealmObject()