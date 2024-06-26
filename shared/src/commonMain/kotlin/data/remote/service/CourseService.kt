package data.remote.service

import data.model.remote.response.CourseResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CourseService(private val baseUrl: String, private val httpClient: HttpClient) {
    suspend fun fetch(): CourseResponse {
        return httpClient.get(baseUrl + "courses").body()
    }
}