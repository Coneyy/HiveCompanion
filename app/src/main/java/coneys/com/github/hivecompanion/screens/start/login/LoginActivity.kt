package coneys.com.github.hivecompanion.screens.start.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import coneys.com.github.core.fragment.CompanionFragment
import coneys.com.github.core.viewmodel.RetriableViewModel
import coneys.com.github.core.viewmodel.ViewModelActivity
import coneys.com.github.hivecompanion.App
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.api.LoadingUserResponse
import coneys.com.github.hivecompanion.api.UserFailureResponse
import coneys.com.github.hivecompanion.api.UserSuccessResponse
import coneys.com.github.hivecompanion.api.UserUnsuccessResponse
import coneys.com.github.hivecompanion.base.activity.ContainerActivity
import coneys.com.github.hivecompanion.screens.intro.IntroActivity
import coneys.com.github.hivecompanion.screens.start.viewModel.StartState
import coneys.com.github.hivecompanion.screens.start.viewModel.StartViewModel
import org.jetbrains.anko.startActivity

class LoginActivity : ContainerActivity(),ViewModelActivity {

    private val companionFragment by lazy { CompanionFragment() }
    private val loginFragment by lazy { LoginFragment() }
    private val startViewModel by lazy { ViewModelProviders.of(this).get(StartViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.registerApiComponent(this)?.inject(startViewModel)
        startViewModel.data.observe(this, Observer { manageData(it) })

        if (savedInstanceState == null)
            showLoginFragment()


    }

    private fun manageData(it: StartState?) {
        it?.let {
            when (it.userResponse) {
                is UserSuccessResponse -> {
                    finishAffinity()
                    startActivity<IntroActivity>()
                }
                is UserUnsuccessResponse -> showLoginFragment()
                is UserFailureResponse -> showCompanionFragment()
                is LoadingUserResponse -> showCompanionFragment()
            }
        }
    }


    private fun showLoginFragment() {
        if (!loginFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, loginFragment)
                    .commit()
    }

    private fun showCompanionFragment() {
        if (!companionFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, companionFragment)
                    .commit()
    }

    override fun getViewModel(): RetriableViewModel = startViewModel


}
