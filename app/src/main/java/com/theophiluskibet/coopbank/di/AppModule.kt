package com.theophiluskibet.coopbank.di

import com.theophiluskibet.coopbank.data.remote.BankApi
import com.theophiluskibet.coopbank.data.remote.BankApiImpl
import org.koin.dsl.module

val appModule = module {
    single<BankApi> { BankApiImpl(httpClient = get()) }
}