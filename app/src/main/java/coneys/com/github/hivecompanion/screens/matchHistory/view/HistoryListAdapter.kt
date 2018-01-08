package coneys.com.github.hivecompanion.screens.matchHistory.view

import coneys.com.github.core.recyclerView.AutoUpdatableAdapter
import coneys.com.github.core.recyclerView.normal.ViewHolderManager
import coneys.com.github.core.recyclerView.normal.specific.MultiViewRecyclerAdapter
import coneys.com.github.hivecompanion.domain.Match
import kotlin.properties.Delegates


class HistoryListAdapter(viewHolderManager: ViewHolderManager<Match>) : MultiViewRecyclerAdapter<Match>(viewHolderManager), AutoUpdatableAdapter {

    var itemList: List<Match> by Delegates.observable((emptyList())) { _, oldList, newList ->
        autoNotify(oldList, newList) { arg0, arg1 ->
            arg0.player1 == arg1.player1 && arg0.player2 == arg1.player2
                    && arg0.player1Points == arg1.player1Points && arg0.player2Points == arg1.player2Points
        }
    }

    override fun getItemCount() = itemList.size

    override fun getItems() = itemList
}