package coneys.com.github.hivecompanion.screens.start.register

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.domain.User
import coneys.com.github.hivecompanion.screens.start.viewModel.StartViewModel
import kotlinx.android.synthetic.main.fragment_register.*
import org.jetbrains.anko.onClick


class RegisterFragment : Fragment() {

    private val startViewModel by lazy { ViewModelProviders.of(activity).get(StartViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_button.onClick {
            startViewModel.register(User(email = register_email.text.toString(), username = register_username.text.toString(), password = register_password.text.toString()))
        }

    }


}