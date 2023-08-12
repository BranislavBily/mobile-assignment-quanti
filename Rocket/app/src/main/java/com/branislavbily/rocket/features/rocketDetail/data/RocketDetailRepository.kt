package com.branislavbily.rocket.features.rocketDetail.data

import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.domain.api.RocketDetailAPIConverterUseCase
import io.reactivex.Single

class RocketDetailRepository(
    private val remoteDataSource: RocketDetailRemoteDataSource,
    private val rocketDetailAPIConverterUseCase: RocketDetailAPIConverterUseCase,
) {
    fun getRocket(id: String): Single<RocketDetail> {
        return remoteDataSource.getRocket(id).map { rocketAPI ->
            rocketDetailAPIConverterUseCase.toModel(rocketAPI)
        }
    }
}
