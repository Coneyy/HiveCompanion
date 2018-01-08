package coneys.com.github.hivecompanion.dependencyInjection

import android.app.Application
import coneys.com.github.hivecompanion.dependencyInjection.api.ApiComponent
import coneys.com.github.hivecompanion.screens.intro.IntroActivity
import coneys.com.github.hivecompanion.screens.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(AppModule::class)
)
interface AppComponent {

    companion object Factory {
        fun create(app: Application): AppComponent {
            val appComponent = DaggerAppComponent.builder().
                    appModule(AppModule(app)).
                    build()
            return appComponent
        }
    }
    fun plusApiComponent(): ApiComponent

    fun inject(splashActivity: SplashActivity)
    fun inject(introActivity: IntroActivity)

}