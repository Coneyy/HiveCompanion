package coneys.com.github.hivecompanion.screens.scoreTable


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
import coneys.com.github.hivecompanion.App
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.domain.UserPoints
import coneys.com.github.hivecompanion.repositories.UserRepository
import kotlinx.android.synthetic.main.fragment_points.*
import javax.inject.Inject

class PointsFragment : Fragment() {

    private val pointsViewHolderManager by lazy { PointsViewHolderManager(layoutInflater) }
    private val pointsListAdapter by lazy { PointsListAdapter(pointsViewHolderManager) }
    private val pointsViewModel by lazy { ViewModelProviders.of(activity).get(PointsViewModel::class.java) }

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater!!.inflate(R.layout.fragment_points, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        toolbar.title = "High score table"
        App.injector.registerPointsComponent(this)?.inject(this)
        pointsViewModel.data.observe(this, Observer { manageData(it) })
    }


    private fun initList() {
        score_list.adapter = pointsListAdapter
        score_list.layoutManager = LinearLayoutManager(context)
    }

    private fun manageData(data: StateData<List<UserPoints>>?) {

        data?.let {

            val state = data.itemBaseState

            if (state == BaseState.SUCCESS) {
                it.item?.let {
                    setPlayerPosition(it)
                    pointsListAdapter.itemList = it
                }
            }
        }


    }

    private fun setPlayerPosition(list: List<UserPoints>) {
        val found = list.firstOrNull {
            it.player.id == userRepository.getCurrentUser()?.id
        }
        if (found != null) {
            val index = list.indexOf(found)
            pointsViewHolderManager.playerPosition = index
            player_position_text.append((index + 1).toString())
        } else
            player_position_text.text = ""
    }

}
