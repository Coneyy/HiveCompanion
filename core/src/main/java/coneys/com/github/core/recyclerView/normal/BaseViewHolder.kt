package coneys.com.github.core.recyclerView.normal

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Extends this class to createParentViewHolder your own ViewHolders for RecyclerView
 */
abstract class BaseViewHolder<in T> protected constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position: Int, item: T)
}