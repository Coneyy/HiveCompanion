package coneys.com.github.hivecompanion.screens.start.register

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
import org.jetbrains.anko.toast

class RegisterActivity : ContainerActivity(),ViewModelActivity {

    private val companionFragment by lazy { CompanionFragment() }
    private val registerFragment by lazy { RegisterFragment() }

    private val startViewModel by lazy { ViewModelProviders.of(this).get(StartViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.registerApiComponent(this)?.inject(startViewModel)
        startViewModel.data.observe(this, Observer { manageData(it) })

        if (savedInstanceState == null) {
            showRegisterFragment()
        }
    }

    override fun getViewModel(): RetriableViewModel {
        return startViewModel
    }

    private fun manageData(it: StartState?) {

        it?.let {
            when (it.userResponse) {
                is UserSuccessResponse -> {
                    finishAffinity()
                    startActivity<IntroActivity>()
                }
                is UserUnsuccessResponse -> {
                    showRegisterFragment()
                    showError(it.userResponse.errorCode)
                }
                is UserFailureResponse -> showCompanionFragment()
                is LoadingUserResponse -> showCompanionFragment()
            }
        }

    }

    private fun showError(errorCode: Int) {

        toast("Error occured $errorCode")
    }

    private fun showRegisterFragment() {
        if (!registerFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, registerFragment)
                    .commit()
    }

    private fun showCompanionFragment() {
        if (!companionFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, companionFragment)
                    .commit()
    }

}
