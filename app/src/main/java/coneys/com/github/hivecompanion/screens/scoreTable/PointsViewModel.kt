package coneys.com.github.hivecompanion.screens.scoreTable

import android.arch.lifecycle.MediatorLiveData
import coneys.com.github.core.state.StateModel
import coneys.com.github.hivecompanion.base.repository.Repository
import coneys.com.github.hivecompanion.base.viewModel.HiveViewModel
import coneys.com.github.hivecompanion.domain.UserPoints
import coneys.com.github.hivecompanion.repositories.PointsRepository
import javax.inject.Inject


class PointsViewModel : HiveViewModel<List<UserPoints>>()
{
    @Inject
    lateinit var pointsRepository: PointsRepository

    override fun retry() {
       getData()
    }

    override fun getState(): MediatorLiveData<StateModel> = data as MediatorLiveData<StateModel>


    override fun getRepository(): Repository<List<UserPoints>> = pointsRepository

}