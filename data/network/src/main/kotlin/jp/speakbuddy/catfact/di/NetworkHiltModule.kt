package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.DefaultJson
import io.ktor.serialization.kotlinx.json.json
import jp.speakbuddy.catfact.network.client.KtorNetworkClient
import jp.speakbuddy.catfact.network.client.NetworkClient
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkHiltModule {

    companion object {

        @Provides
        fun provideHttpClientEngine(): HttpClientEngineFactory<*> = CIO

        @Provides
        fun provideHttpClient(engine: HttpClientEngineFactory<*>): HttpClient =
            HttpClient(engine) {
                install(ContentNegotiation) {
                    json(Json(from = DefaultJson) {
                        ignoreUnknownKeys = true
                    })
                }
            }
    }

    @Singleton
    @Binds
    fun bindsNetworkClient(client: KtorNetworkClient): NetworkClient
}
