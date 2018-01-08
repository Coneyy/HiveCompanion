package coneys.com.github.core.recyclerView.normal


import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Abstract factory class.
 * Extend it and createParentViewHolder view holder for every type of view you want in your list

 * @param <T>
</T> */
abstract class ViewHolderManager<T>
/**
 * LayoutInflater is needed for inflating views for ViewHolders

 * @param layoutInflater
 */
(protected val layoutInflater: LayoutInflater) {


    /**
     * Creates BaseViewHolder which can be used in MultiViewRecyclerAdapter (and GeneralMultiViewRecyclerAdapter)

     */
    abstract fun create(viewType: Int, parent: ViewGroup?): BaseViewHolder<T>

    /**
     * Returnes type of object. Type depends on item type, or its position
     */
    abstract fun getType(item: T, position: Int): Int


}



