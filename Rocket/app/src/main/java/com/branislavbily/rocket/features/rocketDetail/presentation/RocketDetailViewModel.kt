package com.branislavbily.rocket.features.rocketDetail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.branislavbily.rocket.features.rocketDetail.data.RocketDetailRepository
import com.branislavbily.rocket.features.rocketDetail.domain.RocketDetail
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RocketDetailViewModel(
    private val repository: RocketDetailRepository,
) : ViewModel() {

    private val TAG = "RocketDetailViewModel"

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _viewState: MutableStateFlow<RocketDetailScreenState> =
        MutableStateFlow(RocketDetailScreenState())
    val viewState: StateFlow<RocketDetailScreenState> = _viewState

    fun setRocketId(id: String?) {
        Log.i(TAG, "Received id: $id")
        _viewState.update { state -> state.copy(rocketID = id) }
    }

    fun getRocketDetail() {
        viewState.value.rocketID?.let { rocketId ->
            _viewState.update { it.copy(isLoading = true) }
            compositeDisposable.add(
                repository.getRocket(rocketId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .doAfterTerminate {
                        _viewState.update { it.copy(isLoading = false) }
                    }
                    .subscribe({ result ->
                        _viewState.update { state ->
                            Log.i(TAG, result.toString())
                            state.copy(rocketDetail = result)
                        }
                    }, { error ->
                        Log.e(TAG, error.localizedMessage.orEmpty())
                    }),
            )
        }
    }
}

data class RocketDetailScreenState(
    val rocketDetail: RocketDetail = RocketDetail(),
    val isLoading: Boolean = false,
    val rocketID: String? = "bestRocket",
)
