package com.example.jsondatalocaldbapp

import com.example.jsondatalocaldbapp.model.Block
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class RealmModel(var block: Block? = null) : RealmObject()
