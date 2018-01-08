package coneys.com.github.hivecompanion.screens.splash

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import coneys.com.github.hivecompanion.App
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.api.UserFailureResponse
import coneys.com.github.hivecompanion.api.UserSuccessResponse
import coneys.com.github.hivecompanion.api.UserUnsuccessResponse
import coneys.com.github.hivecompanion.repositories.UserRepository
import coneys.com.github.hivecompanion.screens.intro.IntroActivity
import coneys.com.github.hivecompanion.screens.start.login.LoginActivity
import coneys.com.github.hivecompanion.screens.start.viewModel.StartState
import coneys.com.github.hivecompanion.screens.start.viewModel.StartViewModel
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var userRepository: UserRepository

    private val startViewModel by lazy { ViewModelProviders.of(this).get(StartViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val startComponent = App.injector.registerApiComponent(this)
        startComponent?.inject(startViewModel)
        startComponent?.inject(this)

        startViewModel.data.observe(this, Observer { manageData(it) })

        Handler().postDelayed({
            startApplication()
        }, 2000)


    }

    private fun startApplication() {
        val user = userRepository.getCurrentUser()
        if (user != null) {
            startViewModel.login(user)
        } else {
            goToLogin()
        }
    }

    private fun manageData(it: StartState?) {

        it?.let {
            when (it.userResponse) {
                is UserSuccessResponse -> goToIntro()
                is UserFailureResponse -> goToLogin()
                is UserUnsuccessResponse -> goToLogin()
            }
        }
    }

    fun goToIntro() {
        finish()
        startActivity(intentFor<IntroActivity>().clearTop().singleTop())
    }

    fun goToLogin() {
        finish()
        startActivity(intentFor<LoginActivity>().clearTop().singleTop())
    }

}
