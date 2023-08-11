package com.branislavbily.rocket.features.rockets.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import com.branislavbily.rocket.features.rockets.data.RocketsRepository
import io.reactivex.schedulers.Schedulers

class RocketsViewModel(
    private val repository: RocketsRepository,
) : ViewModel() {

    fun getRockets() {
        repository.getRockets()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ result ->
                Log.i("ROCKETS", result.toString())
            }, { error ->
                Log.e("ERROR", error.localizedMessage)
            })
    }
}
