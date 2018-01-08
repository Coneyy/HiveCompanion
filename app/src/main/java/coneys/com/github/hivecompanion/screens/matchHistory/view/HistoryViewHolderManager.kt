package coneys.com.github.hivecompanion.screens.matchHistory.view

import android.view.LayoutInflater
import android.view.ViewGroup
import coneys.com.github.core.recyclerView.normal.BaseViewHolder
import coneys.com.github.core.recyclerView.normal.ViewHolderManager
import coneys.com.github.hivecompanion.R
import coneys.com.github.hivecompanion.domain.Match

class HistoryViewHolderManager(layoutInflater: LayoutInflater) : ViewHolderManager<Match>(layoutInflater) {


    override fun getType(item: Match, position: Int): Int = 0

    override fun create(viewType: Int, parent: ViewGroup?): BaseViewHolder<Match> {

       return HistoryViewHolder(layoutInflater.inflate(R.layout.row_history,parent,false))
    }

}