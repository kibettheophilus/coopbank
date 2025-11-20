package com.theophiluskibet.coopbank.di

import androidx.room.Room
import com.theophiluskibet.coopbank.data.local.database.BankDatabase
import com.theophiluskibet.coopbank.data.remote.BankApi
import com.theophiluskibet.coopbank.data.remote.BankApiImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

const val BASE_URL = "card-services.free.beeceptor.com"

val appModule = module {
    single<BankApi> { BankApiImpl(httpClient = get()) }
    singleOf(::createHttpClient)
    single {
        Room.databaseBuilder(
            context = get(),
            klass = BankDatabase::class.java,
            name = "bank_database.db"
        ).fallbackToDestructiveMigration(dropAllTables = false)
            .build()
    }
}

private fun createHttpClient(engine: HttpClientEngine = OkHttp.create()) =
    HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                },
            )
        }

        install(DefaultRequest) {
            url {
                host = BASE_URL
                protocol = URLProtocol.HTTPS
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }