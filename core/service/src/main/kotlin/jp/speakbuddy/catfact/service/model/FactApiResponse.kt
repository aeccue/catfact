package jp.speakbuddy.catfact.service.model

import kotlinx.serialization.Serializable

@Serializable
data class FactApiResponse(
    val fact: String,
    val length: Int
)
