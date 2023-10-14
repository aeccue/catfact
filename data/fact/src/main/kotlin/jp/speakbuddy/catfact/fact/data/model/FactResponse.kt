package jp.speakbuddy.catfact.fact.data.model

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import javax.inject.Inject

internal class FactResponseSerializer @Inject constructor() : Serializer<FactResponse> {

    override val defaultValue: FactResponse = FactResponse.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): FactResponse {
        try {
            return FactResponse.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: FactResponse, output: OutputStream) = t.writeTo(output)
}

internal inline val FactResponse.asFact get() = Fact(fact = fact, length = length)
