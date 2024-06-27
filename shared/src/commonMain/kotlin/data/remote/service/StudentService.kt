package data.remote.service

import framework.network.safeFetchApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class StudentService(private val baseUrl: String, private val httpClient: HttpClient) {
    suspend fun fetch() = safeFetchApi {
        return@safeFetchApi httpClient.get(baseUrl + "students").body()
    }
}