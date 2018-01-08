package coneys.com.github.hivecompanion.screens.matchHistory.view

import android.view.View
import coneys.com.github.core.recyclerView.normal.BaseViewHolder
import coneys.com.github.hivecompanion.domain.Match
import kotlinx.android.synthetic.main.row_history.view.*

 class HistoryViewHolder(itemView: View) : BaseViewHolder<Match>(itemView) {
    override fun bind(position: Int, item: Match) {
        itemView?.apply {
            player_name.text = item.player1.username
            val scoreText = "${item.player1Points} : ${item.player2Points}"
            score.text = scoreText
        }
    }
 }
