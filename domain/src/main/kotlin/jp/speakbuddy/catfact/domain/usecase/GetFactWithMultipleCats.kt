package jp.speakbuddy.catfact.domain.usecase

import jp.speakbuddy.catfact.repository.model.Fact
import jp.speakbuddy.catfact.repository.FactRepository
import jp.speakbuddy.catfact.domain.model.FactWithMultipleCats
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetFactWithMultipleCats {

    operator fun invoke(): Flow<FactWithMultipleCats>
}

internal class GetFactWithMutlipleCatsUseCase @Inject constructor(
    private val repository: FactRepository
) : GetFactWithMultipleCats {

    override fun invoke(): Flow<FactWithMultipleCats> =
        repository
            .fetch()
            .map { it.withMultipleCats }
}

private const val MULTIPLE_CATS_TERM = "cats"

private fun String.hasMultipleCats(): Boolean = contains(MULTIPLE_CATS_TERM)

private inline val Fact.withMultipleCats: FactWithMultipleCats
    get() = FactWithMultipleCats(
        fact = fact,
        length = length,
        hasMultipleCats = fact.hasMultipleCats()
    )
