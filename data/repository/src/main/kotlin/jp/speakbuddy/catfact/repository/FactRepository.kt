package jp.speakbuddy.catfact.repository

import jp.speakbuddy.catfact.database.dao.FactDao
import jp.speakbuddy.catfact.database.model.FactEntity
import jp.speakbuddy.catfact.repository.model.Fact
import jp.speakbuddy.catfact.service.CatFactService
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
            .map {
                Fact(
                    fact = it.fact,
                    length = it.length
                )
            }

    override suspend fun refresh(): Boolean =
        remoteDataSource.fetch()?.let {
            val entity = FactEntity(
                fact = it.fact,
                length = it.length
            )
            localDataSource.add(entity)
            true
        } ?: false
}
