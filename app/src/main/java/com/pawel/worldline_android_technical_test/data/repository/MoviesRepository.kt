package com.pawel.worldline_android_technical_test.data.repository

import android.util.Log
import com.pawel.worldline_android_technical_test.cache.MoviesInMemoryCache
import com.pawel.worldline_android_technical_test.data.api.ApiHelper
import com.pawel.worldline_android_technical_test.data.model.movies.Result

class MoviesRepository(private val apiHelper: ApiHelper) {

    companion object {
        const val MOVIES_IN_MEMORY_KEY = "movies_in_memory_key"
    }

    private val cache = MoviesInMemoryCache()
    private var existing: List<Result>? = null

    suspend fun getMovies() : List<Result>? {
        existing = cache.fetchFromCacheMemory(MOVIES_IN_MEMORY_KEY)
        val listResult: List<Result>?
        if (existing.isNullOrEmpty()) {
            listResult = apiHelper.getMovies().results
            cache.saveInCacheMemory(MOVIES_IN_MEMORY_KEY, listResult)
            Log.i("TAG", "REPOSI_getMovies: saveInCache::${listResult?.get(0)?.posterPath}")
            Log.i("TAG", "REPOSI_getMovies: saveInCache::${listResult?.get(0)?.id}")
        } else {
            listResult = existing as List<Result>
        }
        return listResult
    }
    suspend fun getMovie(movieID: String) = apiHelper.getMovie(movieID)
}