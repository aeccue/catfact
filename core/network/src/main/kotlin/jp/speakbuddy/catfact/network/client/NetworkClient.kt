package jp.speakbuddy.catfact.network.client

import jp.speakbuddy.catfact.network.response.NetworkResponse
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.typeOf

/**
 * Network client interface for making network requests. Actual implementation is hidden and
 * interchangeable.
 */
internal abstract class NetworkClient {

    suspend inline fun <reified Data : Any> request(
        baseEndpoint: String,
        route: String,
        queries: Map<String, String> = emptyMap()
    ): NetworkResponse<Data> =
        request(
            baseEndpoint = baseEndpoint,
            route = route,
            queries = queries,
            dataClass = Data::class,
            dataType = typeOf<Data>()
        )

    /**
     * KClass and KType passed in as parameters because abstract functions cannot be inlined, and
     * therefore, reified types cannot be used.
     */
    abstract suspend fun <Data : Any> request(
        baseEndpoint: String,
        route: String,
        queries: Map<String, String>,
        dataClass: KClass<Data>,
        dataType: KType
    ): NetworkResponse<Data>
}
