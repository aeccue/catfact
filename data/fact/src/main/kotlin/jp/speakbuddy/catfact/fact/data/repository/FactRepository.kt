package jp.speakbuddy.catfact.fact.data.repository

import jp.speakbuddy.catfact.fact.data.model.Fact
import jp.speakbuddy.catfact.fact.data.model.asFact
import jp.speakbuddy.catfact.fact.data.source.FactLocalDataSource
import jp.speakbuddy.catfact.fact.data.source.FactRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface FactRepository {

    fun fetch(): Flow<Fact>

    suspend fun refresh(): Boolean
}

internal class FactRepositoryImpl @Inject constructor(
    private val localDataSource: FactLocalDataSource,
    private val remoteDataSource: FactRemoteDataSource
) : FactRepository {

    override fun fetch(): Flow<Fact> =
        localDataSource
            .fetch()
            .map { it.asFact }

    override suspend fun refresh(): Boolean =
        remoteDataSource.fetch()?.let { newFact ->
            localDataSource.update(newFact)
            true
        } ?: false
}
