package coneys.com.github.hivecompanion.dependencyInjection.points

import coneys.com.github.hivecompanion.api.AuthorizeCaller
import coneys.com.github.hivecompanion.api.HiveApi
import coneys.com.github.hivecompanion.repositories.PointsRepository
import dagger.Module
import dagger.Provides


@Module
class PointsModule {

    @Provides
    fun providePointsRepository(api: HiveApi, authorizeCaller: AuthorizeCaller): PointsRepository {
        return PointsRepository(api, authorizeCaller)
    }


}