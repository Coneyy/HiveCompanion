package coneys.com.github.hivecompanion.dependencyInjection

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent


class Releaser {
    fun register(owner: LifecycleOwner, injector: Injector) {

        val observer: LifecycleObserver = object :
                LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun destroyed(owner: LifecycleOwner) {

                val toDestroy = ArrayList<Any?>()
                val filter = injector.listOfPairs.filter{
                    it.second == owner }
                filter.mapTo(toDestroy) { it.first }
                injector.listOfPairs.removeAll(filter)

            }
        }
        owner.lifecycle.addObserver(observer)
    }
}