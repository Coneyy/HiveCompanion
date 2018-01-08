package coneys.com.github.hivecompanion.screens.start.login


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.api.UserUnsuccessResponse
import coneys.com.github.hivecompanion.domain.User
import coneys.com.github.hivecompanion.screens.start.register.RegisterActivity
import coneys.com.github.hivecompanion.screens.start.viewModel.StartState
import coneys.com.github.hivecompanion.screens.start.viewModel.StartViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import org.jetbrains.anko.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast


class LoginFragment : Fragment() {


    private val startViewModel by lazy { ViewModelProviders.of(activity).get(StartViewModel::class.java) }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.onClick { doLogin() }
        sing_up_button.onClick { startActivity<RegisterActivity>() }
        startViewModel.data.observe(this, Observer { manageData(it) })
    }

    private fun manageData(it: StartState?) {

        it?.userResponse.let {
            if (it is UserUnsuccessResponse) {
                showError(it.errorCode, it.errorMessage)
            }
        }

    }

    private fun showError(errorCode: Int, errorMessage: String) {
        activity.toast("Error $errorMessage")
    }

    private fun doLogin() {
        val user = User(username = username.text.toString(), password = password.text.toString(), email = "")
        startViewModel.login(user)
    }
}
