package jp.speakbuddy.catfact.fact.data.source

import jp.speakbuddy.catfact.fact.data.model.Fact
import jp.speakbuddy.catfact.network.client.NetworkClient
import jp.speakbuddy.catfact.network.service.CatFactService
import kotlinx.serialization.json.Json
import javax.inject.Inject

interface FactService {

    suspend fun fetch(): Fact?
}

internal class FactServiceProvider @Inject constructor(
    networkClient: NetworkClient,
    json: Json
) : FactService, CatFactService(
    route = ROUTE,
    networkClient = networkClient,
    json = json
) {

    companion object {

        const val ROUTE = "fact"
    }

    override suspend fun fetch(): Fact? = call()
}
