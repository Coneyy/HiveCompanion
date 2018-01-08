package coneys.com.github.core.state


enum class BaseState {
    SUCCESS,
    LOADING,
    FAILURE,
    COMPUTING;

    fun isReady() = this == SUCCESS

}