package jp.speakbuddy.catfact.service

import jp.speakbuddy.catfact.network.BuildConfig
import jp.speakbuddy.catfact.network.client.NetworkClient
import jp.speakbuddy.catfact.network.response.NetworkResponse
import jp.speakbuddy.catfact.service.model.FactApiResponse
import javax.inject.Inject

/**
 * Service for all api calls to cat fact endpoint. It transforms internal api responses to data
 * models used throughout the rest of the app.
 */
interface CatFactService {

    suspend fun fetch(): FactApiResponse?
}

internal class CatFactServiceImpl @Inject constructor(
    private val networkClient: NetworkClient
) : CatFactService {

    private companion object {

        const val BASE_ENDPOINT: String = BuildConfig.CAT_FACT_ENDPOINT
        const val ROUTE_FACT = "fact"
    }

    override suspend fun fetch(): FactApiResponse? = call(route = ROUTE_FACT)

    private suspend inline fun <reified Data : Any> call(
        route: String,
        queries: Map<String, String> = emptyMap()
    ): Data? {
        val response = networkClient.request<Data>(
            baseEndpoint = BASE_ENDPOINT,
            route = route,
            queries = queries
        )
        return when (response) {
            is NetworkResponse.Success -> response.data
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
