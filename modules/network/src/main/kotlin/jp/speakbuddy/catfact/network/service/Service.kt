package jp.speakbuddy.catfact.network.service

import jp.speakbuddy.catfact.network.client.NetworkClient
import jp.speakbuddy.catfact.network.response.NetworkResponse
import kotlinx.serialization.json.Json

/**
 * Abstract API interface that makes network requests with the network client and automatically
 * decode response from JSON.
 */
abstract class Service(
    protected val route: String,
    protected val networkClient: NetworkClient,
    protected val json: Json
) {

    protected suspend inline fun <reified Data : Any> call(
        queries: Map<String, String> = emptyMap()
    ): Data? {
        val response = networkClient.request(
            route = route,
            queries = queries
        )
        return when (response) {
            is NetworkResponse.Success -> json.decodeFromString(response.data)
            is NetworkResponse.ClientError,
            is NetworkResponse.ServerError,
            is NetworkResponse.OtherResponse,
            is NetworkResponse.DeviceError -> {
                // TODO: implement universal network and server error handler
                null
            }
        }
    }
}
