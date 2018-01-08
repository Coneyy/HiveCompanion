package coneys.com.github.core.recyclerView.normal.specific

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import coneys.com.github.core.recyclerView.normal.BaseViewHolder
import coneys.com.github.core.recyclerView.normal.ViewHolderManager

abstract class MultiViewRecyclerAdapter<T>(val viewHolderManager: ViewHolderManager<T>) : RecyclerView.Adapter<BaseViewHolder<T>>() {
    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bind(position, getItems()[position])
    }

    override fun getItemViewType(position: Int): Int {
        return viewHolderManager.getType(getItems()[position], position)

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<T> = viewHolderManager.create(viewType, parent)

    abstract fun getItems(): List<T>
}