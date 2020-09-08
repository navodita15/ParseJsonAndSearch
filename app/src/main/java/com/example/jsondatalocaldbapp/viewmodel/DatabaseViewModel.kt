package com.example.jsondatalocaldbapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.jsondatalocaldbapp.model.Block
import com.example.jsondatalocaldbapp.readAssets
import io.realm.Realm
import io.realm.Sort


private const val TAG = "DatabaseViewModel"
private val blockLiveData: MutableLiveData<List<Block>> by lazy { MutableLiveData<List<Block>>() }

class DatabaseViewModel(application: Application) : AndroidViewModel(application) {

    private val titleList: ArrayList<String> = ArrayList<String>()

    val realm: Realm by lazy {
        Realm.getDefaultInstance()
    }


    private val blocks: List<Block> = readAssets(application.applicationContext)

    init {
        blocks.forEachIndexed { _, block ->
            saveData(block)
        }

    }

    private fun saveData(block: Block) {
        realm.executeTransactionAsync {
            it.insert(block)
            titleList.add(block.block_name!!)
        }
        Log.d(TAG, "saveData: saved")

    }

    private fun readData() {
        val blocks = realm.where(Block::class.java)
            .findAll().sort("block_name", Sort.ASCENDING)
        val list: ArrayList<Block> = ArrayList()
        blocks.forEach {
            list.add(it)

            Log.d(TAG, "readData: $it  \n")
        }
        blockLiveData.value = list

    }

    fun searchData(blockName: String): Boolean {
        val block = realm.where(Block::class.java)
            .equalTo("block_name", blockName)
            .findAll()
        Log.d(TAG, "searchData: block found == $block")
        return !block.isEmpty()

    }

    fun getTitleList() : ArrayList<String>{
        return titleList
    }

    fun cleanUp() {
        onCleared()
    }

    override fun onCleared() {
        realm.close()
        Realm.deleteRealm(realm.configuration)
        super.onCleared()
        Log.d(TAG, "onCleared: called")
    }

    fun getSearchDb(query: String): LiveData<List<Block>> {
        readData()
        return blockLiveData
    }

}


