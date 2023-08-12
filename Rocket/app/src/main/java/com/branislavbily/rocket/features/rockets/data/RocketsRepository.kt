package com.branislavbily.rocket.features.rockets.data

import com.branislavbily.rocket.features.rockets.domain.Rocket
import com.branislavbily.rocket.features.rockets.domain.api.RocketAPIConverterUseCase
import io.reactivex.Single

class RocketsRepository(
    private val remoteDataSource: RocketsRemoteDataSource,
    private val rocketAPIConverterUseCase: RocketAPIConverterUseCase,
) {
    fun getRockets(): Single<List<Rocket>> {
        return remoteDataSource.getRockets().flatMap { result ->
            Single.just(
                result.map { rocketAPI ->
                    rocketAPIConverterUseCase.toModel(rocketAPI)
                },
            )
        }
    }
}
