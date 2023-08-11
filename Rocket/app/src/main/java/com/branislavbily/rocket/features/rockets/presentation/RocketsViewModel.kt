package com.branislavbily.rocket.features.rockets.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.branislavbily.rocket.features.rockets.data.RocketsRepository
import com.branislavbily.rocket.features.rockets.domain.Rocket
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RocketsViewModel(
    private val repository: RocketsRepository,
) : ViewModel() {

    private val TAG = "RocketsViewModel"

    private val _viewState: MutableStateFlow<RocketsScreenState> =
        MutableStateFlow(RocketsScreenState())
    val viewState: StateFlow<RocketsScreenState> = _viewState

    fun getRockets() {
        val joj = repository.getRockets()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ result ->
                _viewState.update { state ->
                    state.copy(rockets = result)
                }
            }, { error ->
                Log.e(TAG, error.localizedMessage)
            })
    }
}

data class RocketsScreenState(
    val rockets: List<Rocket> = listOf(),
)
