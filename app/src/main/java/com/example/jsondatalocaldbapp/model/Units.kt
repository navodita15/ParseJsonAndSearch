package com.example.jsondatalocaldbapp.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmModel
import io.realm.annotations.RealmClass

/*
open class Units(
    @SerializedName("activities") var activities: RealmList<Activities>? = RealmList(),
    @SerializedName("apt") var apt: Int? = null,
    @SerializedName("block_id") var block_id: String? = null,
    @SerializedName("block_name") var block_name: String? = null,
    @SerializedName("floor") var floor: Int? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("property_id") var property_id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("unit_type") var unit_type: String? = null
) : RealmObject() {

    override fun toString(): String {
        return "Units(activities=$activities, apt=$apt, block_id=$block_id, block_name=$block_name, floor=$floor, id=$id, property_id=$property_id, title=$title, unit_type=$unit_type)"
    }
}*/

@RealmClass
open class Units(
    @SerializedName("activities") var activities: RealmList<Activities>? = RealmList(),
    @SerializedName("apt") var apt: Int? = null,
    @SerializedName("block_id") var block_id: String? = null,
    @SerializedName("block_name") var block_name: String? = null,
    @SerializedName("floor") var floor: Int? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("property_id") var property_id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("unit_type") var unit_type: String? = null
) : RealmModel {

    override fun toString(): String {
        return "Units(activities=$activities, apt=$apt, block_id=$block_id, block_name=$block_name, floor=$floor, id=$id, property_id=$property_id, title=$title, unit_type=$unit_type)"
    }
}