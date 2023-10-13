package jp.speakbuddy.catfact.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO
import jp.speakbuddy.catfact.network.client.KtorNetworkClient
import jp.speakbuddy.catfact.network.client.NetworkClient
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface NetworkModule {

    companion object {

        @Singleton
        @Provides
        fun provideJson(): Json = Json {
            encodeDefaults = true
            isLenient = true
            allowSpecialFloatingPointValues = true
            allowStructuredMapKeys = true
            prettyPrint = false
            useArrayPolymorphism = false
            ignoreUnknownKeys = true
        }

        @Provides
        fun provideHttpClientEngine(): HttpClientEngineFactory<*> = CIO

        @Provides
        fun provideHttpClient(engine: HttpClientEngineFactory<*>): HttpClient = HttpClient(engine)
    }

    @Singleton
    @Binds
    fun bindsNetworkClient(client: KtorNetworkClient): NetworkClient
}
