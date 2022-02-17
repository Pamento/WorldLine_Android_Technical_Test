package com.pawel.worldline_android_technical_test.cache

import android.util.Log
import android.util.LruCache
import com.pawel.worldline_android_technical_test.data.model.movies.Result
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesInMemoryCache @Inject constructor() {

    private val maxMemory = Runtime.getRuntime().maxMemory() / 2024
    private val cacheSize = maxMemory / 8
    private val lru: LruCache<String, List<Result>> = LruCache(cacheSize.toInt())

    fun saveInCacheMemory(key: String, data: List<Result>?) {
        try {
            lru.put(key, data)
        } catch (e: Exception) {
            Log.i("ERROR", "on saveInCacheMemory: ${e.localizedMessage}")
        }
    }

    fun fetchFromCacheMemory(key: String) : List<Result>? {
        return try {
            lru.get(key)
        } catch (e: Exception) {
            Log.i("ERROR", "on fetchFromCacheMemory: ${e.localizedMessage}")
            null
        }
    }

}