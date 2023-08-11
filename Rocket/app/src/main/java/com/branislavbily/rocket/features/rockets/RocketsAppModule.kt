package com.branislavbily.rocket.features.rockets

import com.branislavbily.rocket.features.rockets.data.RocketsRemoteDataSource
import com.branislavbily.rocket.features.rockets.data.RocketsRepository
import com.branislavbily.rocket.features.rockets.data.api.RocketsApiDescription
import com.branislavbily.rocket.features.rockets.data.api.RocketsRetrofitDataSource
import com.branislavbily.rocket.features.rockets.presentation.RocketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val rocketsModule = module {
    viewModelOf(::RocketsViewModel)

    single<RocketsApiDescription> {
        get<Retrofit>().create(RocketsApiDescription::class.java)
    }

    single<RocketsRemoteDataSource> {
        RocketsRetrofitDataSource(
            rocketsApiDescription = get(),
        )
    }

    singleOf(::RocketsRepository)
}
