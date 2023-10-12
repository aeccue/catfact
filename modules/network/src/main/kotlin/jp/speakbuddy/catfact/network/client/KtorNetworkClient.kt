package jp.speakbuddy.catfact.network.client

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import jp.speakbuddy.catfact.network.response.NetworkResponse
import javax.inject.Inject


/**
 * Network client that uses the Ktor client
 */
internal class KtorNetworkClient @Inject constructor(
    private val httpClient: HttpClient
) : NetworkClient {

    override suspend fun request(
        route: String,
        queries: Map<String, String>
    ): NetworkResponse {
        try {
            val response = httpClient.get(route) {
                url {
                    for ((parameter, value) in queries) {
                        parameters.append(parameter, value)
                    }
                }
            }

            return when {
                response.status == HttpStatusCode.OK -> NetworkResponse.Success(response.body())

                response.status.value in 400..499 -> NetworkResponse.ClientError(
                    status = response.status.value,
                    body = response.bodyAsText()
                )

                response.status.value in 500..599 -> NetworkResponse.ServerError(
                    status = response.status.value,
                    body = response.bodyAsText()
                )

                else -> NetworkResponse.OtherResponse(
                    status = response.status.value,
                    body = response.bodyAsText()
                )
            }
        } catch (e: Exception) {
            // TODO: Handle individual exceptions themselves for clearer error handling
            return NetworkResponse.DeviceError(e)
        }
    }
}
