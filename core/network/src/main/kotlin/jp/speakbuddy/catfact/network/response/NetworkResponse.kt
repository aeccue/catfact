package jp.speakbuddy.catfact.network.response

sealed interface NetworkResponse {

    /**
     * Returns the data in JSON format
     */
    data class Success(val data: String) : NetworkResponse

    data class ClientError(val status: Int, val body: String?) : NetworkResponse

    data class ServerError(val status: Int, val body: String?) : NetworkResponse

    data class OtherResponse(val status: Int, val body: String?) : NetworkResponse

    /**
     * Device errors include errors such as no network connection
     */
    data class DeviceError(val exception: Exception) : NetworkResponse
}
