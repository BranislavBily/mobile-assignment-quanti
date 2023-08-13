package com.branislavbily.rocket.features.rocketDetail.data

import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import com.branislavbily.rocket.features.rocketDetail.domain.api.RocketDetailAPIConverterUseCase
import io.reactivex.Single

class RocketDetailRepository(
    private val remoteDataSource: RocketDetailRemoteDataSource,
    private val rocketDetailAPIConverterUseCase: RocketDetailAPIConverterUseCase,
) {
    /**
     * Used remote data source to get rocket detail
     *
     * @param id Rocket identifier
     * @return Single value of RocketDetail
     */
    fun getRocketDetail(id: String): Single<RocketDetail> {
        return remoteDataSource.getRocketDetail(id).map { rocketAPI ->
            rocketDetailAPIConverterUseCase.toModel(rocketAPI)
        }
    }
}
