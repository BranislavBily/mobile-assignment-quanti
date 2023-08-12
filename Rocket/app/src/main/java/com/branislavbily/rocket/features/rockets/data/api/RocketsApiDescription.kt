package com.branislavbily.rocket.features.rockets.data.api

import io.reactivex.Single
import retrofit2.http.GET

interface RocketsApiDescription {

    @GET("rockets")
    fun getRockets(): Single<List<RocketAPI>>
}
