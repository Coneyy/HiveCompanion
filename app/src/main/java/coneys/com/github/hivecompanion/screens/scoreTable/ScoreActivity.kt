package coneys.com.github.hivecompanion.screens.scoreTable

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
import coneys.com.github.hivecompanion.domain.UserPoints


class ScoreActivity : ContainerActivity(), ViewModelActivity
{
    private val companionFragment by lazy { CompanionFragment() }
    private val pointsFragment by lazy { PointsFragment() }
    private val viewModel by lazy { ViewModelProviders.of(this).get(PointsViewModel::class.java) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.registerPointsComponent(this)?.inject(viewModel)
        viewModel.data.observe(this, Observer { manageData(it) })
        viewModel.getData()

    }

    private fun manageData(it: StateData<List<UserPoints>>?) {

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

        if (!pointsFragment.isVisible)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, pointsFragment)
                    .commit()
    }

    override fun getViewModel(): RetriableViewModel = viewModel


}