package coneys.com.github.hivecompanion.dependencyInjection.points

import coneys.com.github.hivecompanion.screens.scoreTable.PointsFragment
import coneys.com.github.hivecompanion.screens.scoreTable.PointsViewModel
import dagger.Subcomponent


@Subcomponent(modules = arrayOf(PointsModule::class))
interface PointsComponent {
    fun inject(viewModel: PointsViewModel)
    fun inject(fragment: PointsFragment)
}