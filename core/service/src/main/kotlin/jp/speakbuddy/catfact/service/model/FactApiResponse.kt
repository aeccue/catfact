package jp.speakbuddy.catfact.service.model

import jp.speakbuddy.catfact.data.model.Fact
import kotlinx.serialization.Serializable

@Serializable
internal data class FactApiResponse(
    val fact: String,
    val length: Int
)

internal inline val FactApiResponse.asFact: Fact
    get() = Fact(
        fact = fact,
        length = length
    )
