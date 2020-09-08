package com.example.jsondatalocaldbapp

import android.content.Context
import android.util.Log
import com.example.jsondatalocaldbapp.model.Block
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


fun readAssets(context: Context): List<Block> {
    val jsonFileString = getJsonDataFromAsset(context, JSON_DATA_FILE)

    val gson = Gson()
    val listBlockType = object : TypeToken<List<Block>>() {}.type

    val blocks: List<Block> = gson.fromJson(jsonFileString, listBlockType)
    blocks.forEachIndexed { idx, block ->
        Log.i(
            "BlockName",
            "> Item $idx:\n${block.block_name}"
        )
    }

    return blocks
}


fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        return null
    }
    return jsonString
}





