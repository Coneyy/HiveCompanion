package coneys.com.github.hivecompanion.screens.scoreTable

import android.view.LayoutInflater
import android.view.ViewGroup
import coneys.com.github.core.recyclerView.normal.BaseViewHolder
import coneys.com.github.core.recyclerView.normal.ViewHolderManager
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.domain.UserPoints

class PointsViewHolderManager(layoutInflater: LayoutInflater) : ViewHolderManager<UserPoints>(layoutInflater) {


    override fun getType(item: UserPoints, position: Int): Int {

        return if (position == playerPosition) 1
        else 0
    }

    override fun create(viewType: Int, parent: ViewGroup?): BaseViewHolder<UserPoints> {


        return when (viewType) {
            1 -> PointsViewHolder(layoutInflater.inflate(R.layout.row_player_points, parent, false))
            else -> PointsViewHolder(layoutInflater.inflate(R.layout.row_points, parent, false))
        }

    }

    var playerPosition: Int = -1

}