package com.theophiluskibet.coopbank.di

import android.util.Log
import androidx.room.Room
import com.theophiluskibet.coopbank.data.local.database.BankDatabase
import com.theophiluskibet.coopbank.data.remote.BankApi
import com.theophiluskibet.coopbank.data.remote.BankApiImpl
import com.theophiluskibet.coopbank.data.repositories.BankRepositoryImpl
import com.theophiluskibet.coopbank.domain.repositories.BankRepository
import com.theophiluskibet.coopbank.helpers.LoggerHelper
import com.theophiluskibet.coopbank.presentation.screens.allcards.AllCardsViewModel
import com.theophiluskibet.coopbank.presentation.screens.profile.ProfileViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

const val BASE_URL = "card3.free.beeceptor.com"

val appModule = module {
    single<BankApi> { BankApiImpl(httpClient = get()) }
    singleOf(::createHttpClient)
    singleOf(::providerHttpClientEngine)
    single<BankRepository> { BankRepositoryImpl(bankApi = get(), bankDao = get()) }
    single {
        Room.databaseBuilder(
            context = get(),
            klass = BankDatabase::class.java,
            name = "bank_database.db"
        ).fallbackToDestructiveMigration(dropAllTables = false)
            .build()
    }

    single { get<BankDatabase>().bankDao() }
    viewModelOf(::AllCardsViewModel)
    viewModelOf(::ProfileViewModel)
}

private fun createHttpClient(engine: HttpClientEngine) =
    HttpClient(engine) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                },
            )
        }

        install(Logging) {
            level = LogLevel.ALL
            logger =
                object : Logger {
                    override fun log(message: String) {
                        LoggerHelper(message)
                    }
                }
        }

        install(DefaultRequest) {
            url {
                host = BASE_URL
                protocol = URLProtocol.HTTPS
            }
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }
    }

private fun providerHttpClientEngine(): HttpClientEngine = OkHttp.create()