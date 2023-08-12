package com.branislavbily.rocket.features.rockets

import com.branislavbily.rocket.features.rocketDetail.presentation.RocketDetailViewModel
import com.branislavbily.rocket.features.rockets.data.RocketsRemoteDataSource
import com.branislavbily.rocket.features.rockets.data.RocketsRepository
import com.branislavbily.rocket.features.rockets.data.api.RocketsApiDescription
import com.branislavbily.rocket.features.rockets.data.api.RocketsRetrofitDataSource
import com.branislavbily.rocket.features.rockets.domain.api.RocketAPIConverterUseCase
import com.branislavbily.rocket.features.rockets.domain.api.RocketAPIConverterUseCaseImpl
import com.branislavbily.rocket.features.rockets.presentation.RocketsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val rocketsModule = module {
    viewModelOf(::RocketsViewModel)
    viewModelOf(::RocketDetailViewModel)

    single<RocketsApiDescription> {
        get<Retrofit>().create(RocketsApiDescription::class.java)
    }

    single<RocketsRemoteDataSource> {
        RocketsRetrofitDataSource(
            rocketsApiDescription = get(),
        )
    }
    singleOf(::RocketsRepository)

    factory<RocketAPIConverterUseCase> {
        RocketAPIConverterUseCaseImpl()
    }
}
