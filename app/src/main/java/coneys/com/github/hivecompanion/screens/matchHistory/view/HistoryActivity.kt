package coneys.com.github.hivecompanion.screens.matchHistory.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import coneys.com.github.core.fragment.CompanionFragment
import coneys.com.github.core.state.BaseState
import coneys.com.github.core.state.StateData
import coneys.com.github.core.viewmodel.RetriableViewModel
import coneys.com.github.core.viewmodel.ViewModelActivity
import coneys.com.github.hivecompanion.App
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.base.activity.ContainerActivity
import coneys.com.github.hivecompanion.domain.Match
import coneys.com.github.hivecompanion.screens.matchHistory.viewModel.HistoryViewModel


class HistoryActivity : ContainerActivity(),ViewModelActivity {

    private val companionFragment by lazy { CompanionFragment() }
    private val historyFragment by lazy { HistoryFragment() }
    private val viewModel by lazy { ViewModelProviders.of(this).get(HistoryViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.registerMatchHistoryComponent(this)?.inject(viewModel)
        viewModel.data.observe(this, Observer { manageData(it) })
        viewModel.getData()

    }

    private fun manageData(it: StateData<List<Match>>?) {

        it?.let {

            val state = it.itemBaseState

            when (state) {
                BaseState.LOADING -> showCompanionFragment()
                BaseState.SUCCESS -> showDataFragment()
                BaseState.FAILURE -> showCompanionFragment()
                BaseState.COMPUTING -> {
                }
            }


        }

    }

    private fun showCompanionFragment() {
        if (!companionFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, companionFragment)
                    .commit()
    }

    private fun showDataFragment() {

        if (!historyFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, historyFragment)
                    .commit()
    }

    override fun getViewModel(): RetriableViewModel = viewModel

}
