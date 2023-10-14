package jp.speakbuddy.catfact.fact.data.source

import androidx.datastore.core.DataStore
import jp.speakbuddy.catfact.fact.data.model.FactResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal interface FactLocalDataSource {

    fun fetch(): Flow<FactResponse>

    suspend fun update(fact: FactResponse)
}

internal class FactDataStore @Inject constructor(
    private val dataStore: DataStore<FactResponse>
) : FactLocalDataSource {

    override fun fetch(): Flow<FactResponse> = dataStore.data

    override suspend fun update(newFact: FactResponse) {
        dataStore.updateData { newFact }
    }
}
