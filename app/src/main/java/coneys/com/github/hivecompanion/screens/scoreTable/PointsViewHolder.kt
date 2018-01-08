package coneys.com.github.hivecompanion.screens.scoreTable

import android.view.View
import coneys.com.github.core.recyclerView.normal.BaseViewHolder
import coneys.com.github.hivecompanion.domain.UserPoints
import kotlinx.android.synthetic.main.row_points.view.*

class PointsViewHolder(itemView: View) : BaseViewHolder<UserPoints>(itemView) {
    override fun bind(position: Int, item: UserPoints) {

        itemView?.apply {
            counter.text = (position + 1).toString()
            points_score.text = item.points.toString()
            points_player_name.text = item.player.username
        }
    }

}
