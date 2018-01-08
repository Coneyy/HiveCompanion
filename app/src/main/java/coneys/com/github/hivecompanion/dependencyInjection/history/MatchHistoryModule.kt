package coneys.com.github.hivecompanion.dependencyInjection.history

import coneys.com.github.hivecompanion.api.AuthorizeCaller
import coneys.com.github.hivecompanion.api.HiveApi
import coneys.com.github.hivecompanion.repositories.MatchRepository
import coneys.com.github.hivecompanion.repositories.UserRepository
import dagger.Module
import dagger.Provides


@Module
class MatchHistoryModule {

    @Provides
    fun provideMatchRepository(api: HiveApi, authorizeCaller: AuthorizeCaller, userRepository: UserRepository): MatchRepository {
        return MatchRepository(api, authorizeCaller,userRepository)
    }


}