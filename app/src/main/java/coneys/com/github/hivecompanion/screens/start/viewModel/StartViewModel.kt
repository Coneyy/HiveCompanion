package coneys.com.github.hivecompanion.screens.start.viewModel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.ViewModel
import coneys.com.github.core.state.StateModel
import coneys.com.github.core.viewmodel.RetriableViewModel
import coneys.com.github.hivecompanion.api.LoadingUserResponse
import coneys.com.github.hivecompanion.api.UserSuccessResponse
import coneys.com.github.hivecompanion.domain.User
import coneys.com.github.hivecompanion.repositories.StartRepository
import coneys.com.github.hivecompanion.repositories.UserRepository
import javax.inject.Inject


class StartViewModel : ViewModel(),RetriableViewModel {

    @Inject
    lateinit var startRepository: StartRepository
    @Inject
    lateinit var userRepository: UserRepository

    override fun retry() {
        val userResponse = data.value?.userResponse
        userResponse?.let {
           login(it.user)
        }
    }

    var data: MediatorLiveData<StartState> = MediatorLiveData()
        private set

    fun login(user: User) {
        data.value = StartState(LoadingUserResponse(user))
        data.addSource(startRepository.login(user), {
            it?.let {
                if (it is UserSuccessResponse) {
                    it.user.password = user.password
                    userRepository.saveUser(it.user)
                    userRepository.saveToken(it.token)
                }
                data.value = StartState(it)
            }
        })
    }

    fun register(user: User) {
        data.value = StartState(LoadingUserResponse(user))
        data.addSource(startRepository.register(user), {
            it?.let {
                if (it is UserSuccessResponse) {
                    it.user.password = user.password
                    userRepository.saveUser(it.user)
                    userRepository.saveToken(it.token)
                }
                data.value = StartState(it)
            }
        })

    }


    override fun getState(): MediatorLiveData<StateModel> = data as MediatorLiveData<StateModel>
}