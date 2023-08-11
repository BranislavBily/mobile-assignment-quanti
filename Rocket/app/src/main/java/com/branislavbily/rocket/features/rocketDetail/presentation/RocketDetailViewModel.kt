package com.branislavbily.rocket.features.rocketDetail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RocketDetailViewModel(

) : ViewModel() {

    private val _viewState: MutableStateFlow<RocketDetailScreenState> =
        MutableStateFlow(RocketDetailScreenState())
    val viewState: StateFlow<RocketDetailScreenState> = _viewState

    fun setMovie(id: Int?) {
        Log.i("RocketDetail", id.toString())
        _viewState.update { state -> state.copy(rocketID = id) }
    }
}

data class RocketDetailScreenState(
    val rocketID: Int? = 0,
)
