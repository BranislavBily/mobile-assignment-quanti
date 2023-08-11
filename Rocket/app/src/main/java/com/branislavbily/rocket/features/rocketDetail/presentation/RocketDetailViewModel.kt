package com.branislavbily.rocket.features.rocketDetail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.branislavbily.rocket.features.rocketDetail.data.RocketDetailRepository
import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RocketDetailViewModel(
    private val repository: RocketDetailRepository,
) : ViewModel() {

    private val TAG = "RocketDetailViewModel"

    private val _viewState: MutableStateFlow<RocketDetailScreenState> =
        MutableStateFlow(RocketDetailScreenState())
    val viewState: StateFlow<RocketDetailScreenState> = _viewState

    fun setMovie(id: String?) {
        Log.i(TAG, "Received id: $id")
        id?.let {
            repository.getRocket(it)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe({ result ->
                    _viewState.update { state ->
                        Log.i(TAG, result.toString())
                        state.copy(rocketDetail = result)
                    }
                }, { error ->
                    Log.e(TAG, error.localizedMessage.orEmpty())
                })
        }
        _viewState.update { state -> state.copy(rocketID = id) }
    }
}

data class RocketDetailScreenState(
    val rocketDetail: RocketDetail = RocketDetail(),
    val rocketID: String? = "bestRocket",
)
