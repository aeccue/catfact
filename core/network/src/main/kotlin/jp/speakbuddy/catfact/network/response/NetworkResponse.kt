package jp.speakbuddy.catfact.network.response

internal sealed interface NetworkResponse<Data> {

    /**
     * Returns the data in JSON format
     */
    data class Success<Data>(val data: Data) : NetworkResponse<Data>

    data class ClientError<Data>(val status: Int, val body: String?) : NetworkResponse<Data>

    data class ServerError<Data>(val status: Int, val body: String?) : NetworkResponse<Data>

    data class OtherResponse<Data>(val status: Int, val body: String?) : NetworkResponse<Data>

    /**
     * Device errors include errors such as no network connection
     */
    data class DeviceError<Data>(val exception: Exception) : NetworkResponse<Data>
}
