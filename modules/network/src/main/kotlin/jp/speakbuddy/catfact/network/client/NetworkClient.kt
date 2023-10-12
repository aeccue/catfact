package jp.speakbuddy.catfact.network.client

import jp.speakbuddy.catfact.network.response.NetworkResponse

/**
 * Network client interface for making network requests. Actual implementation is hidden and
 * interchangeable.
 */
interface NetworkClient {

    suspend fun request(
        route: String,
        queries: Map<String, String> = emptyMap()
    ): NetworkResponse
}
