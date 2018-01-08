package coneys.com.github.hivecompanion.screens.scoreTable

import coneys.com.github.core.recyclerView.AutoUpdatableAdapter
import coneys.com.github.core.recyclerView.normal.ViewHolderManager
import coneys.com.github.core.recyclerView.normal.specific.MultiViewRecyclerAdapter
import coneys.com.github.hivecompanion.domain.UserPoints
import kotlin.properties.Delegates


class PointsListAdapter(viewHolderManager: ViewHolderManager<UserPoints>) : MultiViewRecyclerAdapter<UserPoints>(viewHolderManager), AutoUpdatableAdapter {

    var itemList: List<UserPoints> by Delegates.observable((emptyList())) { _, oldList, newList ->
        autoNotify(oldList, newList) { arg0, arg1 ->
            arg0.player == arg1.player
        }
    }

    override fun getItemCount() = itemList.size


    override fun getItems() = itemList
}