package coneys.com.github.hivecompanion.screens.matchHistory.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coneys.com.github.core.state.BaseState
import coneys.com.github.core.state.StateData
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.domain.Match
import coneys.com.github.hivecompanion.screens.matchHistory.viewModel.HistoryViewModel
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private val historyListAdapter by lazy { HistoryListAdapter(HistoryViewHolderManager(layoutInflater)) }
    private val historyViewModel by lazy { ViewModelProviders.of(activity).get(HistoryViewModel::class.java) }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        history_toolbar.title = "Match history"
        historyViewModel.data.observe(this, Observer { manageData(it) })
    }


    private fun initList() {
        match_list.adapter = historyListAdapter
        match_list.layoutManager = LinearLayoutManager(context)
    }

    private fun manageData(data: StateData<List<Match>>?) {

        data?.let {

            val state = data.itemBaseState

            if (state == BaseState.SUCCESS) {
                it.item?.let {
                    historyListAdapter.itemList = it
                }
            }


        }


    }

}
