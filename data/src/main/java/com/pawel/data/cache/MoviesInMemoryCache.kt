package com.pawel.data.cache

import android.util.Log
import android.util.LruCache
import javax.inject.Inject
import javax.inject.Singleton
import com.pawel.domain.model.movies.Result

const val BITE_DIVISION = 2024
const val BITE_BASE = 8

@Singleton
class MoviesInMemoryCache @Inject constructor() {

    private val maxMemory = Runtime.getRuntime().maxMemory() / BITE_DIVISION
    private val cacheSize = maxMemory / BITE_BASE
    private val lru: LruCache<String, List<Result>> = LruCache(cacheSize.toInt())

    @SuppressWarnings("TooGenericExceptionCaught")
    fun saveInCacheMemory(key: String, data: List<Result>?) {
        try {
            lru.put(key, data)
        } catch (e: NullPointerException) {
            Log.e("ERROR", "on saveInCacheMemory: ${e.localizedMessage}")
        }
    }

    fun fetchFromCacheMemory(key: String): List<Result>? {
        return lru.get(key) ?: null
    }
}
