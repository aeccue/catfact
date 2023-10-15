package jp.speakbuddy.catfact.domain.model

data class FactWithMultipleCats(
    val fact: String,
    val length: Int,
    val hasMultipleCats: Boolean
)
