package com.example.jsondatalocaldbapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {


    companion object {
        @JvmField
        var appContext: Context? = null

        // Not really needed since we can access the variable directly.
        @JvmStatic
        fun getAppContext(): Context? {
            return appContext
        }
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        Realm.init(this)
        val config = RealmConfiguration.Builder().build()
        val myConfig = RealmConfiguration.Builder()
            .name("test.db")
            .schemaVersion(1)
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }

}