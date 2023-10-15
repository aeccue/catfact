package jp.speakbuddy.catfact.fact.data.repository

import jp.speakbuddy.catfact.data.model.Fact
import jp.speakbuddy.catfact.database.dao.FactDao
import jp.speakbuddy.catfact.database.model.FactEntity
import jp.speakbuddy.catfact.service.CatFactService
import jp.speakbuddy.catfact.service.model.FactApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FactRepository {

    fun fetch(): Flow<Fact>

    suspend fun refresh(): Boolean
}

internal class FactRepositoryImpl @Inject constructor(
    private val localDataSource: FactDao,
    private val remoteDataSource: CatFactService
) : FactRepository {

    override fun fetch(): Flow<Fact> =
        localDataSource
            .fetchLatest()
            .map { it.asFact }

    override suspend fun refresh(): Boolean =
        remoteDataSource.fetch()?.let { newFact ->
            localDataSource.add(newFact.asEntity)
            true
        } ?: false
}

private inline val FactEntity.asFact: Fact
    get() = Fact(
        fact = fact,
        length = length
    )

private inline val FactApiResponse.asEntity: FactEntity
    get() = FactEntity(
        fact = fact,
        length = length
    )
