package jp.speakbuddy.catfact.network.client

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.appendPathSegments
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfoImpl
import jp.speakbuddy.catfact.network.response.NetworkResponse
import javax.inject.Inject
import kotlin.reflect.KClass
import kotlin.reflect.KType
import kotlin.reflect.javaType

/**
 * Network client that uses the Ktor client
 */
internal class KtorNetworkClient @Inject constructor(
    private val httpClient: HttpClient
) : NetworkClient() {

    override suspend fun <Data : Any> request(
        baseEndpoint: String,
        route: String,
        queries: Map<String, String>,
        dataClass: KClass<Data>,
        dataType: KType
    ): NetworkResponse<Data> {
        try {
            val response = httpClient.get(baseEndpoint) {
                url {
                    appendPathSegments(route)
                    for ((parameter, value) in queries) {
                        parameters.append(parameter, value)
                    }
                }
            }

            return when {
                response.status == HttpStatusCode.OK ->
                    NetworkResponse.Success(response.body(dataClass.typeInfo(dataType)))

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

    /**
     * Uses function provided by ktor to grab type info
     */
    @OptIn(ExperimentalStdlibApi::class)
    private fun <Data : Any> KClass<Data>.typeInfo(type: KType): TypeInfo =
        typeInfoImpl(
            reifiedType = type.javaType,
            this,
            type
        )
}
