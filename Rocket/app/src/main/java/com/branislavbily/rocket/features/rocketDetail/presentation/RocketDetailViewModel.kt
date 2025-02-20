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

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _viewState: MutableStateFlow<RocketDetailScreenState> =
        MutableStateFlow(RocketDetailScreenState())
    val viewState: StateFlow<RocketDetailScreenState> = _viewState

    /**
     * Updates screen state with new rocketDetailId if not null and creates request
     * for Rocket detail based on the rockedDetailId
     *
     * @param rocketDetailId Id of the rocket
     */
    fun getRocketDetail(rocketDetailId: String?) {
        rocketDetailId?.let { rocketId ->
            _viewState.update {
                it.copy(
                    rocketID = rocketId,
                    isLoading = true,
                )
            }
            compositeDisposable.add(
                repository.getRocketDetail(rocketId)
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

    companion object {
        private const val TAG = "RocketDetailViewModel"
    }
}

data class RocketDetailScreenState(
    val rocketDetail: RocketDetail = RocketDetail(),
    val isLoading: Boolean = false,
    val rocketID: String? = "bestRocket",
)
