package com.example.jsondatalocaldbapp.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.RealmClass

/*
open class Block(
    @SerializedName("units") var unit: RealmList<Units>? = RealmList(),
    @SerializedName("block_name") var block_name: String? = null
) : RealmObject(){

    override fun toString(): String {
        return "Block(unit=$unit, block_name=$block_name)"
    }
}*/

@RealmClass
open class Block(
    @SerializedName("units") var unit: RealmList<Units>? = RealmList(),
    @SerializedName("block_name") var block_name: String? = null
) : RealmModel{

    override fun toString(): String {
        return "Block(unit=$unit, block_name=$block_name)"
    }
}