package com.branislavbily.rocket.features.rocketDetail

import com.branislavbily.rocket.features.rocketDetail.data.RocketDetailRemoteDataSource
import com.branislavbily.rocket.features.rocketDetail.data.RocketDetailRepository
import com.branislavbily.rocket.features.rocketDetail.data.api.RocketDetailApiDescription
import com.branislavbily.rocket.features.rocketDetail.data.api.RocketDetailRetrofitDataSource
import com.branislavbily.rocket.features.rocketDetail.domain.api.RocketDetailAPIConverterUseCase
import com.branislavbily.rocket.features.rocketDetail.domain.api.RocketDetailAPIConverterUseCaseImpl
import com.branislavbily.rocket.features.rocketDetail.presentation.RocketDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val rocketDetailModule = module {
    viewModelOf(::RocketDetailViewModel)

    single<RocketDetailApiDescription> {
        get<Retrofit>().create(RocketDetailApiDescription::class.java)
    }

    single<RocketDetailRemoteDataSource> {
        RocketDetailRetrofitDataSource(
            rocketDetailApiDescription = get(),
        )
    }

    factory<RocketDetailAPIConverterUseCase> {
        RocketDetailAPIConverterUseCaseImpl()
    }

    singleOf(::RocketDetailRepository)
}
