package com.pawel.movieapp.conf

import com.pawel.movieapp.data.mock.internal.MockWebServerDispatcher
import com.pawel.data.di.URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class EnvConf {

    @DelicateCoroutinesApi
    @Provides
    @Named(URL)
    fun provideUrl(dispatcher: MockWebServerDispatcher): String {
        val async: Deferred<String> = GlobalScope.async(
            context = Dispatchers.IO,
            block = {
                MockWebServer()
                    .apply {
                        start()
                        this.dispatcher = dispatcher
                    }
                    .let { mockWS ->
                        "http://${mockWS.hostName}:${mockWS.port}"
                    }
            })

        return runBlocking { async.await() }
    }
}
