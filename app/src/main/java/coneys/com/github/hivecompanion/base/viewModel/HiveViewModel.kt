package coneys.com.github.hivecompanion.base.viewModel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import coneys.com.github.core.state.BaseState
import coneys.com.github.core.state.StateData
import coneys.com.github.core.viewmodel.RetriableViewModel
import coneys.com.github.hivecompanion.base.repository.Repository

abstract class HiveViewModel<T> : ViewModel(),RetriableViewModel {
    var data: MediatorLiveData<StateData<T>> = MediatorLiveData()
        private set


    abstract fun getRepository(): Repository<T>

    open fun getData() {
         data.value = StateData(BaseState.LOADING)
            data.addSource(getRepository().fetchData())
            {
                data.value = StateData(it)
            }
        }

}