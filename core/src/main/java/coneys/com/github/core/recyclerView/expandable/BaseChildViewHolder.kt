package coneys.com.github.core.recyclerView.expandable

import android.view.View
import com.bignerdranch.expandablerecyclerview.ChildViewHolder

/**
 * Extends this class to createParentViewHolder your own ViewHolders for child object in ExpandableRecyclerView
 */
abstract class BaseChildViewHolder<C>(itemView: View) : ChildViewHolder<C>(itemView) {

    abstract fun bind(parentPosition: Int, childPosition: Int, child: C)

}