package com.branislavbily.rocket.features.rockets.data

import com.branislavbily.rocket.features.rockets.domain.Rocket
import io.reactivex.Single

class RocketsRepository(
    private val remoteDataSource: RocketsRemoteDataSource,
) {
    fun getRockets(): Single<List<Rocket>> {
        return remoteDataSource.getRockets().flatMap { result ->
            Single.just(
                result.map { rocketAPI ->
                    Rocket(
                        id = rocketAPI.id,
                        name = rocketAPI.name,
                        firstFlight = rocketAPI.firstFlight,
                    )
                },
            )
        }
    }
}
