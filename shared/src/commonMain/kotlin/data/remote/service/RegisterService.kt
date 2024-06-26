package data.remote.service

import data.model.remote.request.RegisterRequest
import data.model.remote.response.RegisterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType


class RegisterService(private val baseUrl: String, private val httpClient: HttpClient) {
    suspend fun fetch(param: RegisterRequest): RegisterResponse {
        return httpClient.post(baseUrl + "register") {
            contentType(ContentType.Application.Json)
            setBody(param)
        }.
        body()
    }
}