package coneys.com.github.core.state


class StateData<T> : StateModel {
    val item: T?
    val itemBaseState: BaseState

    constructor(baseState: BaseState, item: T? = null) {

        this.item = item
        this.itemBaseState = baseState

    }

    constructor(item: T?) {
        this.item = item
        if (item == null)
            this.itemBaseState = BaseState.FAILURE
        else this.itemBaseState = BaseState.SUCCESS
    }

    fun compare(otherState: StateData<T>?, block: () -> Unit) {

        if (this != otherState)
            block.invoke()
    }

    override fun getState(): BaseState = itemBaseState


}

