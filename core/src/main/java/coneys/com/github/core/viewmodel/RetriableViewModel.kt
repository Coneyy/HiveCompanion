package coneys.com.github.core.viewmodel

import android.arch.lifecycle.MediatorLiveData
import coneys.com.github.core.state.StateModel

interface RetriableViewModel {

    fun retry()
    fun getState(): MediatorLiveData<StateModel>

}