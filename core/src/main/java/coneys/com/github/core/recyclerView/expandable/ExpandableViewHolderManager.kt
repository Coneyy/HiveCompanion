package coneys.com.github.core.recyclerView.expandable

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.model.Parent


/**
 * Abstract factory class.
 * Extend it and createParentViewHolder view holders for every type of view you want in your expandable list

 * @param <T>
</T> */
abstract class ExpandableViewHolderManager<P : Parent<C>, C>
/**
 * LayoutInflater is needed for inflating views for ViewHolders

 * @param layoutInflater
 */
(protected val layoutInflater: LayoutInflater) {

    /**
     * Creates BaseParentViewHolder which can be used in MultiViewRecyclerAdapter (and GeneralMultiViewRecyclerAdapter)

     * @see BaseParentViewHolder
     */
    abstract fun createParentViewHolder(viewType: Int, parent: ViewGroup?): BaseParentViewHolder<P, C>

    /**
     * Creates BaseChildViewHolder which can be used in BaseExpandableRecyclerAdapter (and BaseExpandableRecyclerAdapter)

     * @see BaseParentViewHolder
     */
    abstract fun createChildViewHolder(viewType: Int, parent: ViewGroup?): BaseChildViewHolder<C>

    /**
     * Returnes type of child object. Type depends on item type, or its position
     */

    abstract fun getChildType(parentPosition: Int, childPosition: Int, child: C): Int

    /**
     * Returnes type of parent object. Type depends on item type, or its position
     */
    abstract fun getParentType(parentPosition: Int, parent: Parent<C>): Int

    /**
     * If you need more than one type for parent and one type for child, you have to override this method
     * true if viewType is parent, false if it is child
     */
    open fun isParentViewType(viewType: Int): Boolean {
        return viewType == 0
    }


}