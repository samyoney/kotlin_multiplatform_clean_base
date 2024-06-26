package data.remote.service

import data.model.remote.response.StudentResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class StudentService(private val baseUrl: String, private val httpClient: HttpClient) {
    suspend fun fetch(): StudentResponse {
        return httpClient.get(baseUrl + "students").body()
    }
}