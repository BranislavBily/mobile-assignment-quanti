package com.branislavbily.rocket.features.rockets.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.branislavbily.rocket.features.rockets.data.RocketsRepository
import com.branislavbily.rocket.features.rockets.domain.Rocket
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RocketsViewModel(
    private val repository: RocketsRepository,
) : ViewModel() {

    private val TAG = "RocketsViewModel"

    private val compositeDisposable by lazy {
        CompositeDisposable()
    }

    private val _viewState: MutableStateFlow<RocketsScreenState> =
        MutableStateFlow(RocketsScreenState())
    val viewState: StateFlow<RocketsScreenState> = _viewState

    fun getRockets() {
        if (viewState.value.rockets.isEmpty()) {
            _viewState.update { it.copy(isLoading = true) }
            compositeDisposable.add(
                repository.getRockets()
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .doAfterTerminate {
                        _viewState.update { it.copy(isLoading = false) }
                    }
                    .subscribe({ result ->
                        Log.i(TAG, result.toString())
                        _viewState.update { it.copy(rockets = result) }
                    }, { error ->
                        Log.e(TAG, "Error: ${error.localizedMessage.orEmpty()}")
                    }),
            )
        }
    }
}

data class RocketsScreenState(
    val rockets: List<Rocket> = listOf(),
    val isLoading: Boolean = false,
)
