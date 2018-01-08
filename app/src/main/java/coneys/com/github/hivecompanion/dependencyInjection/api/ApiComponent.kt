package coneys.com.github.hivecompanion.dependencyInjection.api

import coneys.com.github.hivecompanion.dependencyInjection.history.MatchHistoryComponent
import coneys.com.github.hivecompanion.dependencyInjection.points.PointsComponent
import coneys.com.github.hivecompanion.screens.splash.SplashActivity
import coneys.com.github.hivecompanion.screens.start.viewModel.StartViewModel
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(ApiModule::class))
interface ApiComponent {

    fun plusHistoryComponent(): MatchHistoryComponent
    fun plusPointsComponent(): PointsComponent

    fun inject(startViewModel: StartViewModel?)
    fun inject(startViewModel: SplashActivity)

}