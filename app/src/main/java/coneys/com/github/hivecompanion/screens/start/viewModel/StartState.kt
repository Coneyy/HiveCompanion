package coneys.com.github.hivecompanion.screens.start.viewModel

import coneys.com.github.core.state.BaseState
import coneys.com.github.core.state.StateModel
import coneys.com.github.hivecompanion.api.*


class StartState(val userResponse: UserResponse) : StateModel {


    override fun getState(): BaseState {
        return when (userResponse) {
            is UserSuccessResponse -> BaseState.SUCCESS
            is UserUnsuccessResponse -> BaseState.SUCCESS
            is UserFailureResponse -> BaseState.FAILURE
            is LoadingUserResponse -> BaseState.LOADING
        }

    }
}
