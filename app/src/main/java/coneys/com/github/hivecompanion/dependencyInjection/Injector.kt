package coneys.com.github.hivecompanion.dependencyInjection

import android.arch.lifecycle.LifecycleOwner
import coneys.com.github.hivecompanion.dependencyInjection.api.ApiComponent
import coneys.com.github.hivecompanion.dependencyInjection.history.MatchHistoryComponent
import coneys.com.github.hivecompanion.dependencyInjection.points.PointsComponent

 class Injector(val appComponent: AppComponent) {

    private val releaser = Releaser()
    internal val listOfPairs: MutableList<Pair<*, LifecycleOwner>> = ArrayList()

    fun registerApiComponent(owner: LifecycleOwner): ApiComponent? {

        var component = listOfPairs.find { it.first is ApiComponent }?.first
        if (component == null) {
            component = appComponent.plusApiComponent()
        }
        listOfPairs.add(component to owner)

        releaser.register(owner, this)

        return component as ApiComponent?

    }

    fun registerMatchHistoryComponent(owner: LifecycleOwner): MatchHistoryComponent? {

        var component = listOfPairs.find { it.first is MatchHistoryComponent }?.first
        if (component == null) {
            component = appComponent.plusApiComponent().plusHistoryComponent()
        }
        listOfPairs.add(component to owner)

        releaser.register(owner, this)

        return component as MatchHistoryComponent?

    }

    fun registerPointsComponent(owner: LifecycleOwner): PointsComponent? {

        var component = listOfPairs.find { it.first is PointsComponent }?.first
        if (component == null) {
            component = appComponent.plusApiComponent().plusPointsComponent()
        }
        listOfPairs.add(component to owner)

        releaser.register(owner, this)

        return component as PointsComponent?

    }

}
