package com.moling.micatoolkit.presentation

import android.util.Log
import dadb.AdbKeyPair

class Global {
    private var globalDict = mutableMapOf<String, Any>()

    init {
        globalDict = mutableMapOf()
    }

    fun set(key: String, value: Any) {
        globalDict.put(key, value)
        Log.d("MICA", "Global | Set [ $key = $value ]")
    }

    fun containsKey(key: String): Boolean {
        return globalDict.containsKey(key)
    }

    private fun getAny(key: String): Any? {
        Log.d("MICA", "Global | Get [ $key ]")
        return globalDict.get(key)
    }

    fun getString(key: String): String {
        return getAny(key) as String
    }

    fun getInt(key: String): Int {
        return getAny(key) as Int
    }

    fun getAdbKeyPair(key: String): AdbKeyPair {
        return getAny(key) as AdbKeyPair
    }
}