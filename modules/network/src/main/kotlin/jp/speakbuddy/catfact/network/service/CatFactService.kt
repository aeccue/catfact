package jp.speakbuddy.catfact.network.service

import jp.speakbuddy.catfact.network.BuildConfig
import jp.speakbuddy.catfact.network.client.NetworkClient
import kotlinx.serialization.json.Json

abstract class CatFactService(
    route: String,
    networkClient: NetworkClient,
    json: Json
) : Service(
    baseEndpoint = BuildConfig.CAT_FACT_ENDPOINT,
    route = route,
    networkClient = networkClient,
    json = json
)
