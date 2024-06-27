package data.remote.service

import data.model.remote.request.LoginRequest
import framework.network.safeFetchApi
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.http.ContentType.Application.Json
import io.ktor.http.contentType

class LoginService(private val baseUrl: String, private val httpClient: HttpClient) {
    suspend fun fetch(param: LoginRequest) = safeFetchApi {
        return@safeFetchApi httpClient.post(baseUrl + "login") {
            contentType(Json)
            setBody(param)
        }.body()
    }
}
