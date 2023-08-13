package com.branislavbily.rocket.core.di

import com.branislavbily.rocket.core.data.RetrofitProvider
import com.branislavbily.rocket.core.domain.CustomDateTimeFormatter
import com.branislavbily.rocket.core.domain.DateFormatter
import org.koin.dsl.module

val coreAppModule = module {
    single {
        RetrofitProvider.provide()
    }

    single<DateFormatter> {
        CustomDateTimeFormatter()
    }
}
