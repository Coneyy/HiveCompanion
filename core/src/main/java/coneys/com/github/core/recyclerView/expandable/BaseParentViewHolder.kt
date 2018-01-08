package coneys.com.github.core.recyclerView.expandable

import android.view.View
import com.bignerdranch.expandablerecyclerview.ParentViewHolder
import com.bignerdranch.expandablerecyclerview.model.Parent


/**
 * Extends this class to createParentViewHolder your own ViewHolders for parent object in ExpandableRecyclerView
 */
abstract class BaseParentViewHolder<P : Parent<C>, C> protected constructor(itemView: View) : ParentViewHolder<P, C>(itemView) {
    abstract fun bind(position: Int, any: P)

}
