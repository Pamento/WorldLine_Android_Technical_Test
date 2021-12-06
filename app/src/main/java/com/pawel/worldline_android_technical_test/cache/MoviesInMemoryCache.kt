package com.pawel.worldline_android_technical_test.cache

import android.util.Log
import android.util.LruCache
import com.pawel.worldline_android_technical_test.data.model.movies.Result

class MoviesInMemoryCache {

    private object HOLDER {
        val INSTANCE = MoviesInMemoryCache()
    }

    companion object {
        val instance: MoviesInMemoryCache by lazy { HOLDER.INSTANCE }
    }

    private val maxMemory = Runtime.getRuntime().maxMemory() / 2024
    private val cacheSize = maxMemory / 8
    private val lru: LruCache<String, List<Result>> = LruCache(cacheSize.toInt())

    fun saveInCacheMemory(key: String, data: List<Result>?) {
        try {
            instance.lru.put(key, data)
        } catch (e: Exception) {
            Log.i("ERROR", "on saveInCacheMemory: ${e.localizedMessage}")
        }
    }

    fun fetchFromCacheMemory(key: String) : List<Result>? {
        return try {
            instance.lru.get(key)
        } catch (e: Exception) {
            Log.i("ERROR", "on fetchFromCacheMemory: ${e.localizedMessage}")
            null
        }
    }

}