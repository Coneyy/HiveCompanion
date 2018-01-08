package coneys.com.github.core.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coneys.com.github.core.R
import coneys.com.github.core.state.BaseState
import coneys.com.github.core.viewmodel.ViewModelActivity
import kotlinx.android.synthetic.main.fragment_companion.*
import org.jetbrains.anko.onClick


class CompanionFragment : Fragment(), ICompanionFragment {


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_companion, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity is ViewModelActivity) {
            val vmActivity = activity as ViewModelActivity
            val viewModel = vmActivity.getViewModel()
            viewModel.getState().observe(this, android.arch.lifecycle.Observer { if (it != null) manageState(it.getState()) })

            errorButton.onClick { viewModel.retry() }
        }
    }

    private fun manageState(baseState: BaseState) {
            when (baseState) {
                BaseState.FAILURE -> showError()
                BaseState.LOADING -> showLoading()
        }

    }


    override fun showError() {
        errorView.visibility = View.VISIBLE
        progressView.visibility = View.GONE
    }

    override fun showLoading() {
        errorView.visibility = View.GONE
        progressView.visibility = View.VISIBLE
    }


}