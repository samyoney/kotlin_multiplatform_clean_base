package data.remote

import data.remote.service.CourseService
import data.remote.service.LoginService
import data.remote.service.RegisterService
import data.remote.service.StudentService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

val provideBaseURL = module {
    single(named("BaseURL")) {
        "https://us-central1-samyoney.cloudfunctions.net/api/"
    }
}

val provideHttpClient = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        prettyPrint = true
                        isLenient = true
                    }
                )
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
    }
}

val provideService = module {
    factory { CourseService(get(named("BaseURL")), get()) }
    factory { LoginService(get(named("BaseURL")), get()) }
    factory { RegisterService(get(named("BaseURL")), get()) }
    factory { StudentService(get(named("BaseURL")), get()) }
}