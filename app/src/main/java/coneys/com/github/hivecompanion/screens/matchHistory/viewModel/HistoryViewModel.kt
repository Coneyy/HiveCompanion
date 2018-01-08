package coneys.com.github.hivecompanion.screens.matchHistory.viewModel

import android.arch.lifecycle.MediatorLiveData
import coneys.com.github.core.state.BaseState
import coneys.com.github.core.state.StateData
import coneys.com.github.core.state.StateModel
import coneys.com.github.hivecompanion.base.viewModel.HiveViewModel
import coneys.com.github.hivecompanion.domain.Match
import coneys.com.github.hivecompanion.repositories.MatchRepository
import coneys.com.github.hivecompanion.repositories.UserRepository
import javax.inject.Inject


class HistoryViewModel : HiveViewModel<List<Match>>() {

    @Inject
    lateinit var matchRepository: MatchRepository

    @Inject
    lateinit var userRepository: UserRepository

    override fun getRepository() = matchRepository

    override fun getState(): MediatorLiveData<StateModel> = data as MediatorLiveData<StateModel>

    override fun retry() {
        getData()
    }

    override fun getData() {
        data.value = StateData(BaseState.LOADING)
        data.addSource(getRepository().fetchData())
        {
            val mapped = it?.map {
                if (it.player1.id == userRepository.getCurrentUser()?.id) {
                    it.copy(player1 = it.player2, player1Points = it.player2Points, player2Points = it.player1Points)
                } else
                    it
            }
            data.value = StateData(mapped)
        }
    }
}
