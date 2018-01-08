package coneys.com.github.core.recyclerView.normal.general

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import coneys.com.github.core.recyclerView.normal.BaseViewHolder
import coneys.com.github.core.recyclerView.normal.ViewHolderManager


abstract class GeneralMultiViewRecyclerAdapter(val viewHolderManager: ViewHolderManager<Any>) : RecyclerView.Adapter<BaseViewHolder<Any>>() {
    override fun onBindViewHolder(holder: BaseViewHolder<Any>, position: Int) {
        holder.bind(position, getItems()[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Any> = viewHolderManager.create(viewType, parent)

    abstract fun getItems(): List<Any>
}