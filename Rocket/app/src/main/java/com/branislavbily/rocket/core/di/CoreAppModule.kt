package com.branislavbily.rocket.core.di

import com.branislavbily.rocket.core.data.RetrofitProvider
import org.koin.dsl.module

val coreAppModule = module {
    single {
        RetrofitProvider.provide()
    }
}
