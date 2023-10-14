package jp.speakbuddy.catfact.fact.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Fact(
    val fact: String,
    val length: Int
)
