package coneys.com.github.hivecompanion.dependencyInjection

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import coneys.com.github.hivecompanion.repositories.UserRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideApplicationContext() = application.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences() = application.applicationContext.getSharedPreferences("preferences", MODE_PRIVATE)

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferences: SharedPreferences) = UserRepository(sharedPreferences)
}