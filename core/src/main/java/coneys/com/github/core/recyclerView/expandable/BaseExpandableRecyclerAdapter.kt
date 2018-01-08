package coneys.com.github.core.recyclerView.expandable

import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter
import com.bignerdranch.expandablerecyclerview.model.Parent


open class BaseExpandableRecyclerAdapter<P : Parent<C>, C>(parentList: MutableList<P>, val viewHolderManager: ExpandableViewHolderManager<P, C>) : ExpandableRecyclerAdapter<P, C, BaseParentViewHolder<P, C>, BaseChildViewHolder<C>>(parentList) {
    override fun onCreateParentViewHolder(parentViewGroup: ViewGroup, viewType: Int): BaseParentViewHolder<P, C> {
        return viewHolderManager.createParentViewHolder(viewType, parentViewGroup)
    }

    override fun onBindParentViewHolder(parentViewHolder: BaseParentViewHolder<P, C>, parentPosition: Int, parent: P) {
        parentViewHolder.bind(parentPosition, parent)
    }

    override fun onCreateChildViewHolder(childViewGroup: ViewGroup, viewType: Int): BaseChildViewHolder<C> {
        return viewHolderManager.createChildViewHolder(viewType, childViewGroup)
    }

    override fun onBindChildViewHolder(childViewHolder: BaseChildViewHolder<C>, parentPosition: Int, childPosition: Int, child: C) {
        childViewHolder.bind(parentPosition, childPosition, child)
    }

    override fun getChildViewType(parentPosition: Int, childPosition: Int): Int {
        return viewHolderManager.getChildType(parentPosition, childPosition, parentList[parentPosition].childList[childPosition])
    }

    override fun getParentViewType(parentPosition: Int): Int {
        return viewHolderManager.getParentType(parentPosition, parentList[parentPosition])
    }

    override fun isParentViewType(viewType: Int): Boolean {
        return viewHolderManager.isParentViewType(viewType)
    }
}



