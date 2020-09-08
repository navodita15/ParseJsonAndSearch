package com.example.jsondatalocaldbapp.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.RealmClass

/*
open class Activities(
    @SerializedName("activity_name") var activity_name: String? = null,
    @SerializedName("activity_status") var activity_status: String? = null,
    @SerializedName("current_user_name") var current_user_name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("step_name") var step_name: String? = null,
    @SerializedName("progress") var progress: Int? = null,
    @SerializedName("wf") var wf: String? = null
) : RealmObject() {

    override fun toString(): String {
        return "Activities(activity_name=$activity_name, activity_status=$activity_status, current_user_name=$current_user_name, id=$id, step_name=$step_name, progress=$progress, wf=$wf)"
    }
}*/
@RealmClass
open class Activities(
    @SerializedName("activity_name") var activity_name: String? = null,
    @SerializedName("activity_status") var activity_status: String? = null,
    @SerializedName("current_user_name") var current_user_name: String? = null,
    @SerializedName("id") var id: String? = null,
    @SerializedName("step_name") var step_name: String? = null,
    @SerializedName("progress") var progress: Int? = null,
    @SerializedName("wf") var wf: String? = null
) : RealmModel {

    override fun toString(): String {
        return "Activities(activity_name=$activity_name, activity_status=$activity_status, current_user_name=$current_user_name, id=$id, step_name=$step_name, progress=$progress, wf=$wf)"
    }
}