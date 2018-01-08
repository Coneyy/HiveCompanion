package coneys.com.github.hivecompanion.api

import android.os.Handler
import coneys.com.github.hivecompanion.repositories.StartRepository
import coneys.com.github.hivecompanion.repositories.UserRepository

class AuthorizeCaller(private val startRepository: StartRepository, private val userRepository: UserRepository) {


    fun getAuthorization(failureCallback: () -> Unit, successCallback: (String) -> Unit) {
        val loginResponse = startRepository.login(userRepository.getCurrentUser())
        loginResponse.observeForever({
            when (it) {
                is UserSuccessResponse -> {
                    Handler().postDelayed({
                        successCallback.invoke(it.token)
                    }, 2000)
                }
                is UserFailureResponse -> failureCallback.invoke()
                is UserUnsuccessResponse -> failureCallback.invoke()
            }
        })

    }


}
