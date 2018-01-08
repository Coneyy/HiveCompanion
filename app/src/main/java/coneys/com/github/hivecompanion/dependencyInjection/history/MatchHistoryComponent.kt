package coneys.com.github.hivecompanion.dependencyInjection.history

import coneys.com.github.hivecompanion.screens.matchHistory.viewModel.HistoryViewModel
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MatchHistoryModule::class))
interface MatchHistoryComponent {
    fun inject(viewModel: HistoryViewModel)
}